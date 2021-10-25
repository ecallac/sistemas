/**
 * 
 */
package com.security;

import java.util.Date;

/**
 * @author EFRAIN
 * @dateCreated 15 may. 2017 21:39:51
 */
public class Session {
	private Long id;
	private User user;
	private Date loginDate;
	private Date logoutDate;
	private String sessionKey;
	private String hostAddress;
	private Module module;
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the loginDate
	 */
	public Date getLoginDate() {
		return loginDate;
	}
	/**
	 * @param loginDate the loginDate to set
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	/**
	 * @return the logoutDate
	 */
	public Date getLogoutDate() {
		return logoutDate;
	}
	/**
	 * @param logoutDate the logoutDate to set
	 */
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}
	/**
	 * @return the sessionKey
	 */
	public String getSessionKey() {
		return sessionKey;
	}
	/**
	 * @param sessionKey the sessionKey to set
	 */
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	/**
	 * @return the hostAddress
	 */
	public String getHostAddress() {
		return hostAddress;
	}
	/**
	 * @param hostAddress the hostAddress to set
	 */
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	
}
