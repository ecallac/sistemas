/**
 * 
 */
package com.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.BusinessException;
import com.security.domain.Role;
import com.security.domain.RoleUser;
import com.security.domain.User;
import com.security.repository.RoleUserRepository;
import com.security.repository.UserRepository;
import com.security.util.BeanParser;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
    UserRepository userRepository;
	
//	@Autowired
//	RoleUserRepository roleUserRepository;
	
	@Override
	public List<User> findList() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get();
	}

	@Override
	public void save(User entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
            User enrityFromDb = findById(entity.getId());
            entity.setVersion(enrityFromDb.getVersion()+1);
            enrityFromDb = (User) BeanParser.parseBetweenObjects(entity, enrityFromDb);
            enrityFromDb.setDateUpdated(new Date());
            userRepository.save(enrityFromDb);
        }else{
        	entity.setVersion(0);
            entity.setDateCreated(new Date());
            userRepository.save(entity);
        }
	}

	@Override
	public User findByUsernameAndPassword(String userName, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> findByStatus(String status) {
		// TODO Auto-generated method stub
		return userRepository.findByStatus(status);
	}

	@Override
	public void savePassword(User user) throws Exception {
		// TODO Auto-generated method stub
		User userStored = findByUserName(user.getUserName());
		if (userStored==null) {
			throw new BusinessException("User not exist!");
		}
		userStored.setPassword(user.getPassword());
		userStored.setUpdatedBy(user.getUpdatedBy());
		userStored.setDateUpdated(new Date());
		userRepository.save(userStored);
	}

	@Override
	public void savePasswordById(User user) {
		// TODO Auto-generated method stub
		User userStored = findById(user.getId());
		userStored.setPassword(user.getPassword());
		userStored.setUpdatedBy(user.getUpdatedBy());
		userStored.setDateUpdated(new Date());
		userRepository.save(userStored);
	}

	
}
