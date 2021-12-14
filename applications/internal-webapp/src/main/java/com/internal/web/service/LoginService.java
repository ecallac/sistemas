/**
 * 
 */
package com.internal.web.service;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.common.Persona;
import com.internal.web.service.integration.PermissionIntegration;
import com.internal.web.service.integration.PersonaIntegration;
import com.internal.web.service.integration.UserIntegration;
import com.internal.web.utils.Constants;
import com.security.Permission;
import com.security.Role;
import com.security.Session;
import com.security.User;


/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 13:17:20
 */
@Service
public class LoginService implements UserDetailsService {
	static final String ROLE_PREFIX_SPRING_ECURITY="ROLE_";
	@Autowired
	UserIntegration userIntegration;
	
	@Autowired
	PersonaIntegration personaIntegration;
	
	@Autowired
	PermissionIntegration permissionIntegration;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User userBean = userIntegration.findByUserNameActive(userName);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userBean.getUserName(), userBean.getPassword(), getGrantedAuthorityList(userBean.getRoles()));
		return userDetails;
	}
	
	protected List<GrantedAuthority> getGrantedAuthorityList(List<Role> roleBeans){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (roleBeans!=null) {
			for (Role roleBean: roleBeans) {
				GrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX_SPRING_ECURITY+roleBean.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}
	
	public Map<String,String> getPermissionByRole() throws Exception{
		List<Permission> permissions= permissionIntegration.findEnabledListByModuleName(Constants.MODULE);
		Map<String,List<String>> permissionBeans = new HashMap<String,List<String>>();
		for (Permission permission : permissions) {
			List<Role> rolesDB = permission.getRoles();
			List<String> roles = new ArrayList<String>();
			for (Role role : rolesDB) {
				roles.add(ROLE_PREFIX_SPRING_ECURITY+role.getName());
			}
			permissionBeans.put(permission.getPath(), roles);	
		}
		Map<String, String> map = new HashMap<String, String>();
		for (Map.Entry<String,List<String>> entry : permissionBeans.entrySet()){
			map.put(entry.getKey(), "'" + StringUtils.join(entry.getValue(), "','") + "'");
		}
		System.out.println(map);
		return map;
	}
	
	
	public List<Permission> getPermissions(){
		List<Permission> permissions= permissionIntegration.findEnabledListByModuleName(Constants.MODULE);
		List<Permission> list = new ArrayList<Permission>();
		for (Permission permission : permissions) {
			if (permission.getParentPermission()!=null) {
				list.add(permission);
			}
		}
		return list;
	}
	
	public void addSessionObjects(HttpSession httpSession,Principal principal) {
		List sessionlist = (List) httpSession.getAttribute("permissions");
		if (CollectionUtils.isEmpty(sessionlist)) {
			httpSession.setAttribute("permissions", getPermissions());
		}
		
		User user =  (User) httpSession.getAttribute("user");
		if (user==null) {
			user = userIntegration.findByUserNameActive(principal.getName());
			httpSession.setAttribute("user", user);
			Persona  person = (Persona) httpSession.getAttribute("person");
			if (person==null) {
				person = personaIntegration.findByEntidadRolId(user.getEntidadRoleId());
				httpSession.setAttribute("person", person);
			}
			
		}
	}
	
	public void saveSession(Session session) {
		userIntegration.saveSession(session);
	}
}
