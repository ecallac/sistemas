/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Role;
import com.security.domain.RoleUser;

/**
 * @author efrain.calla
 *
 */
public interface RoleService extends BaseService<Role> {
	List<Role> findEnabledList();
	List<Role> findListByPermissionId(Long permissionId);
}
