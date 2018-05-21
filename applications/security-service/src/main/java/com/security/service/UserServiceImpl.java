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
import com.security.domain.Role;
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
	
	@Autowired
	RoleService roleService;

	public void save(User user) {
		if (user.getId()==null) {
			userDao.save(user);
		}else{
			User userStored = findUserById(user.getId());
			if (userStored!=null) {
				userStored = (User) BeanParser.parseBetweenObjects(user, userStored, null);
				userDao.save(userStored);
			}
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

	@Override
	public void savePasswordById(User user){
		User userStored = findUserById(user.getId());
		userStored.setPassword(user.getPassword());
		userStored.setUpdatedBy(user.getUpdatedBy());
		userDao.save(userStored);
		
	}

	@Override
	public void saveRoleInUser(Long userId, Long roleId) {
		User user = findUserById(userId);
    	Role role = roleService.findRoleById(roleId);
    	role.getUsers().add(user);
    	user.getRoles().add(role);
    	save(user);
	}

	@Override
	public void deleteRoleFromUser(Long userId, Long roleId) {
		User user = findUserById(userId);
		Role roleStored = roleService.findRoleById(roleId);
		user.getRoles().remove(roleStored);
		roleStored.getUsers().remove(user);
		roleService.save(roleStored);
		save(user);
		
	}
	
	
}
