/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Permission;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 12:44:00
 */
public interface PermissionService {
	void save(Permission permission);
	void delete(Permission permission);
	Permission findPermissionById(Long id);
	List<Permission> findAllPermissions();
	List<Permission> findPermissionsByEnabled(String enabled);
	List<Permission> findPermissionsByModuleId(Long id);
	List<Permission> findPermissionByRoleId(Long id);
}
