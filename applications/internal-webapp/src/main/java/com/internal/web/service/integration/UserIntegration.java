/**
 * 
 */
package com.internal.web.service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.security.Session;
import com.security.User;

/**
 * @author efrain.calla
 *
 */
@Service
public class UserIntegration extends ServiceIntegrationAbstract<User> {
	@Autowired
	@Value("${app.security.api}")
	private String appsecurityapi;
	
	String basePath="user";
	
	public User findByUserNameActive(String userName) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findByUserNameActive?userName="+userName, User.class);
	}
	public void savePasswordById(User bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/savePasswordById", bean);
	}
	public void saveSession(Session bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/saveSession", bean);
	}
}
