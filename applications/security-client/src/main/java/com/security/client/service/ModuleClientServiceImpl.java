/**
 * 
 */
package com.security.client.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.security.client.bean.ModuleBean;
import com.security.client.canonical.ModuleCanonicalRequest;
import com.security.client.canonical.ModuleCanonicalResponse;
import com.security.client.canonical.UserCanonicalResponse;
import com.security.client.utils.RestUtils;

/**
 * @author efrain
 *
 */
public class ModuleClientServiceImpl implements ModuleClientService {
	private static final Log log = LogFactory.getLog(UserClientServiceImpl.class);
	private Map<String, String> urls;
	public Map<String, String> getUrls()
	  {
	    return this.urls;
	  }
	  
	  public void setUrls(Map<String, String> urls)
	  {
	    this.urls = urls;
	  }

	/* (non-Javadoc)
	 * @see com.security.client.service.ModuleClientService#getRolesOfPermissionByModule(java.lang.String)
	 */
	public ModuleBean getRolesOfPermissionByModule(String module) throws Exception {
		log.info(":::: Started ModuleService.getRolesOfPermissionByModule ::::");
		ModuleCanonicalRequest request = new ModuleCanonicalRequest();
	    request.setModuleName(module);
	    
	    ModuleCanonicalResponse response = (ModuleCanonicalResponse)RestUtils.sendXMLPostRequest((String)this.urls.get(ROLES_BY_MODULE_URL), request, ModuleCanonicalRequest.class, ModuleCanonicalResponse.class);
	    if (response != null){
	    	if (response.getStatus().equals(UserCanonicalResponse.STATUS_ERROR)) {
				throw new Exception(response.getMessage());
			}
	    	if (response.getModuleBean() != null){
    			ModuleBean responseObject = response.getModuleBean();
		        return responseObject;
	    	}
	    }else{
	    	throw new Exception("There aren't response.");
	    }
		log.info(":::: Finished ModuleService.getRolesOfPermissionByModule ::::");
		return null;
	}

}
