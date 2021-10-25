/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Role;

/**
 * @author efrain.calla
 *
 */
public interface RoleService extends BaseService<Role> {
	List<Role> findEnabledList();
}
