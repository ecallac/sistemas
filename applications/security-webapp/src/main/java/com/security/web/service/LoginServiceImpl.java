/**
 * 
 */
package com.security.web.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.Module;
import com.security.Permission;
import com.security.Role;
import com.security.User;
import com.security.web.service.integration.ModuleIntegration;
import com.security.web.service.integration.PermissionIntegration;
import com.security.web.service.integration.UserIntegration;
import com.security.web.utils.SecurityConstants;


/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 13:17:20
 */
@Service
public class LoginServiceImpl implements UserDetailsService {
	static final String ROLE_PREFIX_SPRING_ECURITY="ROLE_";
//	@Autowired
//	@Value("${security.module.rolesbymodule.xml}")
//	private String securityRolesByModule;
	
	@Autowired
	UserIntegration userIntegration;
	
	@Autowired
	PermissionIntegration permissionIntegration;
	
	@Autowired
	ModuleIntegration moduleIntegration;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User userBean = userIntegration.findByUserName(userName);
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
		Module module = moduleIntegration.findByName(SecurityConstants.MODULE_SECURITY);
		List<Permission> permissions= permissionIntegration.findEnabledListByModuleId(module.getId());
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
}
