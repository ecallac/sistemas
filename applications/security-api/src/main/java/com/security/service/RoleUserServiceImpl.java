/**
 * 
 */
package com.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.Role;
import com.security.domain.RoleUser;
import com.security.domain.RoleUserKey;
import com.security.domain.User;
import com.security.repository.RoleUserRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class RoleUserServiceImpl implements RoleUserService {

	@Autowired
	RoleUserRepository roleUserRepository;
	@Override
	public void save(User user, Role role) {
		// TODO Auto-generated method stub
		RoleUserKey entryPK = new RoleUserKey();
	    entryPK.setUserId(user.getId());
	    entryPK.setRoleId(role.getId());
	    RoleUser entry = new RoleUser();
	    entry.setId(entryPK);
	    entry.setUser(user);
	    entry.setRole(role);
	    roleUserRepository.save(entry);
	}
	
	@Override
	public List<User> findListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return getUsers(roleUserRepository.findByRoleId(roleId));
	}

	private List<User> getUsers(List<RoleUser> roleUsrs){
		List<User> roles = new ArrayList<User>();
		for (RoleUser roleUser : roleUsrs) {
			roles.add(roleUser.getUser());
		}
		return roles;
	}
	
	@Override
	public List<Role> findListByUserId(Long userId) {
		// TODO Auto-generated method stub
		return getRolesByUser(roleUserRepository.findByUserId(userId));
	}

	private List<Role> getRolesByUser(List<RoleUser> roleUsrs){
		List<Role> roles = new ArrayList<Role>();
		for (RoleUser roleUser : roleUsrs) {
			roles.add(roleUser.getRole());
		}
		return roles;
	}

	@Override
	public void delete(User user, Role role) {
		RoleUserKey entryPK = new RoleUserKey();
	    entryPK.setUserId(user.getId());
	    entryPK.setRoleId(role.getId());
	    RoleUser entry = new RoleUser();
	    entry.setId(entryPK);
	    entry.setUser(user);
	    entry.setRole(role);
	    roleUserRepository.delete(entry);
	}

}
