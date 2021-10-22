/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.security.Module;


/**
 * @author efrain.calla
 *
 */
@Service
public class ModuleIntegration extends ServiceIntegrationAbstract<Module> {
	@Autowired
	@Value("${app.security.api}")
	private String appsecurityapi;
	
	String basePath="module";
	
	public Module findById(Long id) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findById?id="+id, Module.class);
	}
	public List<Module> findEnabledList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findEnabledList",new TypeReference<List<Module>>(){});
	}
	public List<Module> findList() {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findList",new TypeReference<List<Module>>(){});
	}
	public void save(Module bean) {
		setObjectToPostRequest(appsecurityapi+"/"+basePath+"/save", bean);
	}
	public Module findByName(String name) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findByName?name="+name, Module.class);
	}
}
