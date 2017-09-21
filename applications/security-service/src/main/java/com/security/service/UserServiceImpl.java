/**
 * 
 */
package com.security.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.utils.BeanParser;

//import org.apache.log4j.Logger;

import com.security.dao.UserDao;
import com.security.domain.BusinessException;
import com.security.domain.User;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
//	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserDao userDao;

	public void save(User user) {
		if (user.getId()==null) {
			userDao.save(user);
		}else{
			User userStored = findUserById(user.getId());
			userStored = (User) BeanParser.parseBetweenObjects(user, userStored, null);
			userDao.save(userStored);
		}
		
	}

	public void delete(User user) {
		userDao.delete(user);
		
	}

	public User findUserById(Long id) {
		// TODO Auto-generated method stub
		return (User) userDao.findById(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userDao.findAll(User.class);
	}

	public User findUserByUsernameAndPassword(String userName, String password) {
		// TODO Auto-generated method stub
		return userDao.findUserByUsernameAndPassword(userName, password);
	}

	public User findUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserByUserName(userName);
	}

	public List<User> findUsersByStatus(String status) {
		// TODO Auto-generated method stub
		return userDao.findUsersByStatus(status);
	}

	@Override
	public void savePassword(User user) throws Exception{
		User userStored = findUserByUserName(user.getUserName());
		if (userStored==null) {
			throw new BusinessException("User not exist!");
		}
		userStored.setPassword(user.getPassword());
		userStored.setUpdatedBy(user.getUpdatedBy());
		userDao.save(userStored);
	}
	
	
}
