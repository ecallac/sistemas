/**
 * 
 */
package com.security.client.service;

import java.util.Map;

import com.security.client.bean.UserBean;

/**
 * @author efrain.calla
 *
 */
public interface UserClientService {
	static final String USER_BY_USERNAME_URL = "USER_BY_USERNAME_URL";
	void setUrls(Map<String, String> urls);
	UserBean getUserByUserName(String userName) throws Exception;
}
