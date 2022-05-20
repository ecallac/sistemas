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
	private String api;
	
	String basePath="user";
	
	public User findByUserName(String userName) {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByUserName?userName="+userName, User.class);
	}
	public User findByUserNameActive(String userName) {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByUserNameActive?userName="+userName, User.class);
	}
	public void savePasswordById(User bean) {
		setObjectToPostRequest(api+"/"+basePath+"/savePasswordById", bean);
	}
	public void saveSession(Session bean) {
		setObjectToPostRequest(api+"/"+basePath+"/saveSession", bean);
	}
	public User findById(Long id) {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, User.class);
	}
}
