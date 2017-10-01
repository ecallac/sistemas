/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.Role;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 13:13:02
 */
public interface RoleService {
	List<Role> findRolesByUserName(String userName);
	List<Role> findRolesByUserId(Long id);
	Role findRoleById(Long id);
	List<Role> findAllRoles();
	void save(Role role);
	void delete(Role role);
}
