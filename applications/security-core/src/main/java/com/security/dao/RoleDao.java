/**
 * 
 */
package com.security.dao;

import java.util.List;

import com.security.dao.common.BaseDao;
import com.security.domain.Role;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 12:53:28
 */
public interface RoleDao extends BaseDao{
	List<Role> findRolesByUserName(String userName);
	List<Role> findRolesByUserId(Long id);
	List<Role> findByEnabled(String enabled);
//	Role findRoleById(Long id);
//	List<Role> findAllRoles();
}
