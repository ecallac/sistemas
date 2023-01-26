/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Permission;
import com.security.domain.Role;
import com.security.domain.RolePermission;

/**
 * @author efrain.calla
 *
 */
public interface RolePermissionService {
	void save(Role role, Permission permission);
	void deleteByRoleId(Long roleId);
	List<Permission> findPermissionListByRoleId(Long roleId);
	List<Role> findRoleListByPermissionId(Long permissionId);

	List<Permission> findRootPermissionListByRoleIdAndModuleName(Long roleId, String moduleName);
}
