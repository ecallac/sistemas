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
public interface UserService extends BaseService<User>{
	User findByUsernameAndPassword(String userName,String password);
	User findByUserName(String userName);
	User findByUserNameActive(String userName);
	List<User> findByStatus(String status);
	void savePassword(User user) throws Exception;
	void savePasswordById(User user);
	
}
