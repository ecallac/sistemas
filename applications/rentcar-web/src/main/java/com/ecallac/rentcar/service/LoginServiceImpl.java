/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ecallac.rentcar.bean.NavigationLinkBean;
import com.ecallac.rentcar.domain.Rol;
import com.ecallac.rentcar.domain.Usuario;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 13:17:20
 */
@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Usuario userBean = getUser(userName);
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userBean.getUsername(), userBean.getPassword(), getGrantedAuthorityList(userBean.getRol()));
		return userDetails;
	}
	
	protected List<GrantedAuthority> getGrantedAuthorityList(Rol rol){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(rol.getNameWithPrefix());
		authorities.add(authority);
		return authorities;
	}

	protected Usuario getUser(String userName) throws UsernameNotFoundException{
			Usuario usuario =  usuarioService.findByUsername(userName);
			if (usuario==null) {
				throw new UsernameNotFoundException("No se encuentra usuario.");
			} else {
				return usuario;
			}
	}
	
	public Map<String,String> getPermissionByRole() throws Exception{
		Map<String,List<String>> permissionBeans = new HashMap<>();
		
			List<Rol> rolesDB = rolService.findAllByStatus("Y");
			
			List<String> roles = new ArrayList<>();
			for (Rol role : rolesDB) {
				roles.add(role.getNameWithPrefix());
			}
			permissionBeans.put("/", roles);
			permissionBeans.put("/home", roles);
			permissionBeans.put("/marca", roles);
			permissionBeans.put("/modelo", roles);
			permissionBeans.put("/clase", roles);
		
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
	
	
	public static List<NavigationLinkBean> getNavigationLinkList(List<NavigationLinkBean> linkBeans,String path,String name,int level){
		NavigationLinkBean lastLinkBean = new NavigationLinkBean();
		lastLinkBean.setPath(path);
		lastLinkBean.setName(name);
		lastLinkBean.setLevel(level);
		lastLinkBean.setActive(true);
		
		List<NavigationLinkBean> navigationLinkBeans = new ArrayList<NavigationLinkBean>();
		if (CollectionUtils.isEmpty(linkBeans)) {
			navigationLinkBeans.add(lastLinkBean);
		}else {
			boolean added = false;
			for (int i = 0; i < linkBeans.size(); i++) {
				if (lastLinkBean.getLevel()==i) {
					navigationLinkBeans.add(lastLinkBean);
					added= true;
				}else if (lastLinkBean.getLevel()<i) {
					navigationLinkBeans.remove(i);
				}else {
					NavigationLinkBean linkBean = linkBeans.get(i);
					linkBean.setActive(false);
					navigationLinkBeans.add(linkBeans.get(i));
				}
			}
			if (!added) {
				navigationLinkBeans.add(lastLinkBean);
			}
		}
		return navigationLinkBeans;
	}
}
