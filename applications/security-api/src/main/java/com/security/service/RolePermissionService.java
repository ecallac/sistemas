/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Permission;
import com.security.domain.Role;

/**
 * @author efrain.calla
 *
 */
public interface RolePermissionService {
	void save(Role role, Permission permission);
	void deleteByRoleId(Long roleId);
	List<Permission> findPermissionListByRoleId(Long roleId);
	List<Role> findRoleListByPermissionId(Long permissionId);
}
