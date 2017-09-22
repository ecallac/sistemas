/**
 * 
 */
package com.security.web.service;

import static com.security.client.service.UserClientService.USER_BY_USERNAME_URL;
import static com.security.utils.SecurityConstants.MODULE_SECURITY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.client.bean.RoleBean;
import com.security.client.bean.UserBean;
import com.security.client.service.UserClientService;
import com.security.client.service.UserClientServiceImpl;
import com.security.domain.Module;
import com.security.domain.Permission;
import com.security.domain.Role;
import com.security.service.ModuleService;
import com.security.service.PermissionService;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 13:17:20
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	PermissionService permissionService;
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	@Value("${security.user.userbyusername.xml}")
	private String securityUserByUserName;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserBean userBean = getUser(userName);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userBean.getUserName(), userBean.getPassword(), getGrantedAuthorityList(userBean.getRoles()));
		return userDetails;
	}
	
	protected List<GrantedAuthority> getGrantedAuthorityList(List<RoleBean> roleBeans){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (roleBeans!=null) {
			for (RoleBean roleBean: roleBeans) {
				GrantedAuthority authority = new SimpleGrantedAuthority(roleBean.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	protected UserBean getUser(String userName) throws UsernameNotFoundException{
		Map<String, String> urls =new HashMap<String, String>();
		urls.put(USER_BY_USERNAME_URL, securityUserByUserName);
		UserClientService client = new UserClientServiceImpl();
		client.setUrls(urls);
		try {
			return client.getUserByUserName(userName);
		} catch (Exception e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
	
	public Map<String,String> getPermissionByRole(){
		
		Module module = moduleService.findByName(MODULE_SECURITY);
		List<Permission> permissions = permissionService.findPermissionsByModuleId(module.getId());
		
		Map<String,List<String>> permissionBeans = new HashMap<>();
		
		for (Permission permission : permissions) {
			List<Role> rolesDB = permission.getRoles();
			
			List<String> roles = new ArrayList<>();
			for (Role role : rolesDB) {
				roles.add(role.getNameWithPrefix());
			}
			permissionBeans.put(permission.getPath(), roles);	
		}
		
		Map<String, String> map = new HashMap<>();
		
		int c = 1;
		for (Map.Entry<String,List<String>> entry : permissionBeans.entrySet()){
			List<String> list = entry.getValue();
			
			StringBuffer buffer = new StringBuffer();
			for (String string : list) {
				if (c > 1) {
					buffer.append(",");
				}
				buffer.append("'"+string+"'");
				c++;
			}
			map.put(entry.getKey(), buffer.toString());
			c=0;
		}
		
		System.out.println(map);
		return map;
	}
}
