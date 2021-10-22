/**
 * 
 */
package com.security.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author EFRAIN
 * @dateCreated 15 may. 2017 21:39:51
 */
@Entity
@Table(name = "session")
public class Session {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_Id")
	private User user;
	
	@Column(name = "logindate")
	private Date loginDate;
	
	@Column(name = "logoutdate")
	private Date logoutDate;
	
	@Column(name = "sessionkey")
	private String sessionKey;
	
	@Column(name = "hostaddress")
	private String hostAddress;
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
