/**
 * 
 */
package com.security.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.security.domain.User;

/**
 * @author EFRAIN
 * @dateCreated 4 mar. 2017 22:48:09
 */
public class UserDaoTest extends AbstractUnitTest{
	
	@Autowired
	UserDao userDao;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFindAllUsers(){
		List<User> users =(List<User>) userDao.findAll(User.class);
		logger.info("#####"+users);
	}
}
