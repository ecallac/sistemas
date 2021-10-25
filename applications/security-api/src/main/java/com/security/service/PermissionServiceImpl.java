/**
 * 
 */
package com.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.Module;
import com.security.domain.Permission;
import com.security.domain.RolePermission;
import com.security.enums.EnableIndicator;
import com.security.repository.PermissionRepository;
import com.security.repository.RolePermissionRepository;
import com.security.util.BeanParser;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionRepository permissionRepository;
	
	@Autowired
	RolePermissionRepository rolePermissionRepository;
	/* (non-Javadoc)
	 * @see com.security.service.PermissionService#findEnabledPermissionsByModuleId(java.lang.Long)
	 */
	@Override
	public List<Permission> findEnabledPermissionsByModuleId(Long moduleId) {
		// TODO Auto-generated method stub
		return permissionRepository.findEnabledPermissionsByModuleId(moduleId);
	}
	@Override
	public List<Permission> findList() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}
	@Override
	public Permission findById(Long id) {
		// TODO Auto-generated method stub
		return permissionRepository.findById(id).get();
	}
	@Override
	public void save(Permission entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Permission enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
            enrityFromDb = (Permission) BeanParser.parseBetweenObjects(entity, enrityFromDb);
            enrityFromDb.setDateUpdated(new Date());
            permissionRepository.save(enrityFromDb);
        }else{
        	entity.setDateCreated(new Date());
        	permissionRepository.save(entity);
        }
	}
	@Override
	public List<Permission> findEnabledList() {
		// TODO Auto-generated method stub
		return permissionRepository.findAllByEnabledOrderByDescription(EnableIndicator.ENABLED.getCode());
	}
	@Override
	public List<Permission> findListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return getUsers(rolePermissionRepository.findByRoleId(roleId));
	}
	
	private List<Permission> getUsers(List<RolePermission> rolePermissions){
		List<Permission> roles = new ArrayList<Permission>();
		for (RolePermission rolePermission : rolePermissions) {
			roles.add(rolePermission.getPermission());
		}
		return roles;
	}
	@Override
	public List<Permission> findEnabledPermissionsByModuleName(String moduleName) {
		// TODO Auto-generated method stub
		return permissionRepository.findAllByModuleNameAndEnabledOrderByDescription(moduleName, EnableIndicator.ENABLED.getCode());
	}

}
