/**
 * 
 */
package com.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<User> findUserListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return roleUserRepository.findByRoleId(roleId).stream().map(l->l.getUser()).collect(Collectors.toList());
	}
	
	@Override
	public List<Role> findRoleListByUserId(Long userId) {
		// TODO Auto-generated method stub
		return roleUserRepository.findByUserId(userId).stream().map(l->l.getRole()).collect(Collectors.toList());
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
