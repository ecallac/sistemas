/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Role;
import com.security.domain.User;

/**
 * @author efrain.calla
 *
 */
public interface RoleUserService{
	void save(User user, Role role);
	void delete(User user, Role role);
	List<User> findListByRoleId(Long roleId);
	List<Role> findListByUserId(Long userId);
}
