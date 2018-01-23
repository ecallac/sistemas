/**
 * 
 */
package com.security.client.service;

import java.util.Map;

import com.security.client.bean.ModuleBean;

/**
 * @author efrain
 *
 */
public interface ModuleClientService {
	static final String ROLES_BY_MODULE_URL = "ROLES_BY_MODULE_URL";
	void setUrls(Map<String, String> urls);
	ModuleBean getRolesOfPermissionByModule(String module) throws Exception;
	
}
