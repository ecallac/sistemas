/**
 * 
 */
package com.security.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.client.bean.RoleBean;
import com.security.client.bean.UserBean;
import com.security.client.canonical.UserCanonicalRequest;
import com.security.client.canonical.UserCanonicalResponse;
import com.security.client.utils.XmlUtils;
import com.security.domain.Role;
import com.security.domain.User;
import com.security.service.RoleService;
import com.security.service.UserService;

/**
 * @author efrain.calla
 *
 */
@RestController
public class UserRestController {
	/**
	 * get User With Roles and permission by username
	 * 
	 * 
	 */
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	
	@RequestMapping(value = "/getUserByUserName", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public String getUserByUserName(@RequestBody UserCanonicalRequest request) {
		System.out.println(":::getUserByUserName");
        User user = userService.findUserByUserName(request.getUserName());
        if (user==null) {
        	System.out.println(":::error");
        	UserCanonicalResponse userCanonicalResponse = new UserCanonicalResponse();
        	userCanonicalResponse.setStatus(UserCanonicalResponse.STATUS_ERROR);
        	userCanonicalResponse.setMessage("User Name was not found in the database!");
        	return XmlUtils.toXml(UserCanonicalResponse.class,userCanonicalResponse);
        }
        List<Role> roles = roleService.findRolesByUserName(request.getUserName());
        return  XmlUtils.toXml(UserCanonicalResponse.class,convertUserToUserCanonicalResponse(user, roles));
    }
	
	public UserCanonicalResponse convertUserToUserCanonicalResponse(User user,List<Role> roles){
		UserCanonicalResponse userCanonicalResponse = new UserCanonicalResponse();
		UserBean bean = new UserBean();
		bean.setUserName(user.getUserName());
		bean.setPassword(user.getPassword());
		List<RoleBean> rolesList = new ArrayList<>();
		for (Role role : roles) {
			RoleBean roleBean = new RoleBean();
			roleBean.setName(role.getNameWithPrefix());
			rolesList.add(roleBean);
		}
		bean.setRoles(rolesList);
		userCanonicalResponse.setUserBean(bean);
		userCanonicalResponse.setStatus(UserCanonicalResponse.STATUS_OK);
		return userCanonicalResponse;
	}
}
