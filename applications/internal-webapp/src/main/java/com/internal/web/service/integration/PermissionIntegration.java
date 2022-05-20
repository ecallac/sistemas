/**
 * 
 */
package com.internal.web.service.integration;

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
	private String api;
	
	String basePath="permission";
	
	public List<Permission> findEnabledListByModuleName(String moduleName) {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findEnabledListByModuleName?moduleName="+moduleName,new TypeReference<List<Permission>>(){});
	}
}
