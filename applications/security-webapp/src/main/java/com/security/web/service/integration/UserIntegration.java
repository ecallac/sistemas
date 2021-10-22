/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	
	public User findByUserName(String userName) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findByUserName?userName="+userName, User.class);
	}
	public User findById(Long id) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findById?id="+id, User.class);
	}
	public List<User> findList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findList",new TypeReference<List<User>>(){});
	}
	public void save(User bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/save", bean);
	}
	public void savePasswordById(User bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/savePasswordById", bean);
	}
	public void addRoleUserAssociation(Long userId,Long roleId) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/addRoleUserAssociation?userId="+userId+"&roleId="+roleId, null);
	}
	public void removeRoleUserAssociation(Long userId,Long roleId) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/removeRoleUserAssociation?userId="+userId+"&roleId="+roleId, null);
	}
}
