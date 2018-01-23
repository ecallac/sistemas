/**
 * 
 */
package com.security.client.canonical;

import com.security.client.bean.ModuleBean;

/**
 * @author efrain
 *
 */
public class ModuleCanonicalResponse {
	public static String STATUS_OK = "OK";
	public static String STATUS_ERROR = "ERROR";
	private ModuleBean moduleBean;
	private String status;
	private String message;
	
	public ModuleBean getModuleBean() {
		return moduleBean;
	}
	public void setModuleBean(ModuleBean moduleBean) {
		this.moduleBean = moduleBean;
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
