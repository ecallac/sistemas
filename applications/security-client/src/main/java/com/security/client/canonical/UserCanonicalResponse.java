/**
 * 
 */
package com.security.client.canonical;

import com.security.client.bean.UserBean;

/**
 * @author efrain.calla
 *
 */
public class UserCanonicalResponse {
	public static String STATUS_OK = "OK";
	public static String STATUS_ERROR = "ERROR";
	private UserBean userBean;
	private String status;
	private String message;
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
