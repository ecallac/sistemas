/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.security.Permission;


/**
 * @author efrain.calla
 *
 */
@Service
public class PermissionIntegration extends ServiceIntegrationAbstract<Permission> {
	@Autowired
	@Value("${app.security.api}")
	private String appsecurityapi;
	
	String basePath="permission";
	
	public List<Permission> findEnabledListByModuleId(Long moduleId) {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findEnabledListByModuleId?moduleId="+moduleId,new TypeReference<List<Permission>>(){});
	}
	public Permission findById(Long id) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findById?id="+id, Permission.class);
	}
	public List<Permission> findEnabledList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findEnabledList",new TypeReference<List<Permission>>(){});
	}
	public List<Permission> findList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findList",new TypeReference<List<Permission>>(){});
	}
	public void save(Permission bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/save", bean);
	}
}
