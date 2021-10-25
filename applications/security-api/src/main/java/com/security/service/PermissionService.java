/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Permission;

/**
 * @author efrain.calla
 *
 */
public interface PermissionService extends BaseService<Permission>{
	List<Permission> findEnabledPermissionsByModuleId(Long moduleId);
	List<Permission> findEnabledList();
	List<Permission> findListByRoleId(Long roleId);
	List<Permission> findEnabledPermissionsByModuleName(String moduleName);
}
