/**
 * 
 */
package com.security.dao;

import java.util.List;

import com.security.dao.common.BaseDao;
import com.security.domain.User;

/**
 * @author efrain.calla
 *
 */
public interface UserDao extends BaseDao{
//	void saveUser(User user);
//	void deleteUsers(List<User> users);
//	void deleteUser(User user);
//	User findUserById(Long id);
//	List<User> findAllUsers();
	User findUserByUsernameAndPassword(String userName,String password);
	User findUserByUserName(String userName);
	List<User> findUsersByStatus(String status);
}
