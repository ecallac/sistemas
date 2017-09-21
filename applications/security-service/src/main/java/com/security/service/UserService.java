/**
 * 
 */
package com.security.service;

import java.util.List;

import com.security.domain.User;

/**
 * @author efrain.calla
 *
 */
public interface UserService {
	void save(User user);
	void delete(User user);
	User findUserById(Long id);
	List<User> findAllUsers();
	User findUserByUsernameAndPassword(String userName,String password);
	User findUserByUserName(String userName);
	List<User> findUsersByStatus(String status);
	void savePassword(User user) throws Exception;
}
