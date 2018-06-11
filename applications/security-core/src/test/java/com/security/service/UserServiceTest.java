/**
 * 
 */
package com.security.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.security.domain.User;

/**
 * @author EFRAIN
 * @dateCreated 4 mar. 2017 22:48:09
 */
public class UserServiceTest extends AbstractUnitTest{
	
	@Autowired
	UserService userService;
	
	@Test
	public void testFindAllUsers(){
		List<User> users =userService.findAllUsers();
		logger.info("#####"+users);
	}
}
