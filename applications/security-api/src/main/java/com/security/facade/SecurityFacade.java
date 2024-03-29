/**
 * 
 */
package com.security.facade;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.security.domain.Module;
import com.security.domain.Permission;
import com.security.domain.Role;
import com.security.domain.Session;
import com.security.domain.User;
import com.security.service.ModuleService;
import com.security.service.PermissionService;
import com.security.service.RolePermissionService;
import com.security.service.RoleService;
import com.security.service.RoleUserService;
import com.security.service.SessionService;
import com.security.service.UserService;
import org.springframework.util.CollectionUtils;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional(readOnly = true)
public class SecurityFacade {
	@Autowired
    ModuleService moduleService;
	@Autowired
    UserService userService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	@Autowired
	RoleUserService roleUserService;
	@Autowired
	RolePermissionService rolePermissionService;
	@Autowired
	SessionService sessionService;
	
	public User findUserByUserName(String userName) {
		User user = userService.findByUserName(userName);
		return user!=null?setRolesToUser(user):null;
	}
	
	public User findUserByUserNameActive(String userName) {
		User user = userService.findByUserNameActive(userName);
		return user!=null?setRolesToUser(user):null;
	}
	
	private User setRolesToUser(User user) {
		user.setRoles(roleUserService.findRoleListByUserId(user.getId()));
		return user;
	}
	
	public List<Permission> findEnabledPermissionsByModuleId(Long moduleId) {
		return setRolesToPermissionList(permissionService.findEnabledPermissionsByModuleId(moduleId));
	}
	
	public List<Permission> findEnabledPermissionsByModuleName(String moduleName) {
		return setRolesToPermissionList(permissionService.findEnabledPermissionsByModuleName(moduleName));
	}
	
	private List<Permission> setRolesToPermissionList(List<Permission> permissions){
		for (Permission permission : permissions) {
			permission = setRolesToPermission(permission);
		}
		return permissions;
	}
	private Permission setRolesToPermission(Permission permission) {
		permission.setRoles(rolePermissionService.findRoleListByPermissionId(permission.getId()));
		return permission;
	}

	public List<Permission> findPermissionListByRoleIdAndModuleName(Long roleId, String moduleName){
		List<Permission> permissions = rolePermissionService.findRootPermissionListByRoleIdAndModuleName(roleId,moduleName);
		fillChildPermissions(permissions);
		return permissions;
	}

	private void fillChildPermissions(List<Permission> permissions){

		for (Permission permission : permissions){
			List<Permission> children = permissionService.findAllByParentPermissionId(permission.getId());
			for (Permission child : children){
				child.setParentPermission(null);
			}
			if (!CollectionUtils.isEmpty(children)){
				permission.setChildPermissions(children);
				fillChildPermissions(children);
			}
		}
	}
	
	
	public Module findModuleByName(String name) {
		return moduleService.findByName(name);
	}
	
	public Module findModuleById(Long id) {
		return moduleService.findById(id);
	}
	
	public List<Module> findModuleEnabledList() {
		return moduleService.findEnabledList();
	}
	
	public List<Module> findModuleList() {
		return moduleService.findList();
	}
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveModule(Module entity) {
		moduleService.save(entity);
    }
	
	
	
	public Role findRoleById(Long id) {
		Role role = roleService.findById(id);
		role.setPermissions(permissionService.findListByRoleId(role.getId()));
		return role;
	}
	
	public List<Role> findRoleEnabledList() {
		return roleService.findEnabledList();
	}
	
	public List<Role> findRoleList() {
		return roleService.findList();
	}
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveRole(Role entity) {
		roleService.save(entity);
    }
	
	
	
	public Permission findPermissionById(Long id) {
		return permissionService.findById(id);
	}
	
	public List<Permission> findPermissionEnabledList() {
		return permissionService.findEnabledList();
	}
	
	public List<Permission> findPermissionList() {
		return permissionService.findList();
	}
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void savePermission(Permission entity) {
		permissionService.save(entity);
    }
	
	
	public User findUserById(Long id) {
		User user = userService.findById(id);
		user.setRoles(roleUserService.findRoleListByUserId(user.getId()));
		return user;
	}
	
	public List<User> findUserList() {
		return userService.findList();
	}
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveUser(User entity) {
		userService.save(entity);
    }
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveUserPasswordById(User user) {
		userService.savePasswordById(user);
	}
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveRoleInUser(Long userId, Long roleId) {
		roleUserService.save(findUserById(userId), findRoleById(roleId));
	}
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void deleteRoleInUser(Long userId, Long roleId) {
		roleUserService.delete(findUserById(userId), findRoleById(roleId));
	}
	
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveRolePermission(Role role) {
		rolePermissionService.deleteByRoleId(role.getId());
		Role roledb = roleService.findById(role.getId());
		for (Permission permission : role.getPermissions()) {
			rolePermissionService.save(roledb, permissionService.findById(permission.getId()));
		}
		
	}
	
	@Transactional(readOnly = false,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveSession(Session entity) {
		Session sessionDB = sessionService.findBySessionKey(entity.getSessionKey());
		if (sessionDB == null) {
			entity.setModule(findModuleByName(entity.getModule().getName()));
			entity.setUser(findUserByUserName(entity.getUser().getUserName()));
			sessionService.save(entity);
		}
    }
}
