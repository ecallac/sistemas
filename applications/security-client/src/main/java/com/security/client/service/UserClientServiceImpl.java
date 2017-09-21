/**
 * 
 */
package com.security.client.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.security.client.bean.UserBean;
import com.security.client.canonical.UserCanonicalRequest;
import com.security.client.canonical.UserCanonicalResponse;
import com.security.client.utils.RestUtils;

/**
 * @author efrain.calla
 *
 */
public class UserClientServiceImpl implements UserClientService{
	
	private static final Log log = LogFactory.getLog(UserClientServiceImpl.class);
	private Map<String, String> urls;
	
	public UserBean getUserByUserName(String userName) throws Exception{
	    log.info(":::: Started UserService.getUserByUserName ::::");
	    
	    UserCanonicalRequest request = new UserCanonicalRequest();
	    request.setUserName(userName);
	    
	    UserCanonicalResponse response = (UserCanonicalResponse)RestUtils.sendXMLPostRequest((String)this.urls.get(USER_BY_USERNAME_URL), request, UserCanonicalRequest.class, UserCanonicalResponse.class);
	    if (response != null){
	    	if (response.getStatus().equals(UserCanonicalResponse.STATUS_ERROR)) {
				throw new Exception(response.getMessage());
			}
	    	if (response.getUserBean() != null){
    			UserBean responseObject = response.getUserBean();
		        return responseObject;
	    	}
	    }else{
	    	throw new Exception("There aren't response.");
	    }
	    log.info(":::: Finished UserService.getUserByUserName ::::");
	    return null;
	  }
	public Map<String, String> getUrls()
	  {
	    return this.urls;
	  }
	  
	  public void setUrls(Map<String, String> urls)
	  {
	    this.urls = urls;
	  }
	  
}
