/**
 * 
 */
package com.security.client;

import com.security.client.canonical.UserCanonicalRequest;
import com.security.client.canonical.UserCanonicalResponse;
import com.security.client.utils.RestUtils;

/**
 * @author efrain.calla
 *
 */
public class AppMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserCanonicalRequest request = new UserCanonicalRequest();
	    request.setUserName("user.user");
	    
	    UserCanonicalResponse response = (UserCanonicalResponse)RestUtils.sendXMLPostRequest("http://localhost:8080/security-rest/getUserByUserName.xml", request, UserCanonicalRequest.class, UserCanonicalResponse.class);
	    System.out.println(response);

	}

}
