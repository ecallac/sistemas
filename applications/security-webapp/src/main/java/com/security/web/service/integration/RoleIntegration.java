/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.security.Role;


/**
 * @author efrain.calla
 *
 */
@Service
public class RoleIntegration extends ServiceIntegrationAbstract<Role> {
	@Autowired
	@Value("${app.security.api}")
	private String appsecurityapi;
	
	String basePath="role";
	
	public Role findById(Long id) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findById?id="+id, Role.class);
	}
	public List<Role> findEnabledList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findEnabledList",new TypeReference<List<Role>>(){});
	}
	public List<Role> findList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findList",new TypeReference<List<Role>>(){});
	}
	public void save(Role bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/save", bean);
	}
	public void savePermissionAssociation(Role bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/saveRolePermissionAssociation", bean);
	}
}
