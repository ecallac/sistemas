/**
 * 
 */
package com.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.Permission;
import com.security.domain.Role;
import com.security.domain.RolePermission;
import com.security.domain.RolePermissionKey;
import com.security.domain.User;
import com.security.repository.RolePermissionRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	RolePermissionRepository rolePermissionRepository;

	/* (non-Javadoc)
	 * @see com.security.service.RolePermissionService#findPermissionListByRoleId(java.lang.Long)
	 */
	@Override
	public List<Permission> findPermissionListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return rolePermissionRepository.findByRoleId(roleId).stream().map(l -> l.getPermission()).collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.security.service.RolePermissionService#findRoleListByPermissionId(java.lang.Long)
	 */
	@Override
	public List<Role> findRoleListByPermissionId(Long permissionId) {
		// TODO Auto-generated method stub
		return rolePermissionRepository.findByPermissionId(permissionId).stream().map(l->l.getRole()).collect(Collectors.toList());
	}

	@Override
	public void save(Role role, Permission permission) {
		// TODO Auto-generated method stub
		RolePermissionKey entryPK = new RolePermissionKey();
	    entryPK.setPermissionId(role.getId());
	    entryPK.setRoleId(permission.getId());
	    RolePermission entry = new RolePermission();
	    entry.setId(entryPK);
	    entry.setPermission(permission);
	    entry.setRole(role);
	    rolePermissionRepository.save(entry);
	}
	
	@Override
	public void deleteByRoleId(Long roleId) {
		rolePermissionRepository.deleteByRoleId(roleId);
	}

	@Override
	public List<Permission> findRootPermissionListByRoleIdAndModuleName(Long roleId, String moduleName) {
		return rolePermissionRepository.findByRoleIdAndPermissionModuleName(roleId,moduleName).stream().map(l->l.getPermission()).filter(l -> l.getParentPermission()!=null && l.getParentPermission().getPath().equals("/")).collect(Collectors.toList());
	}
}
