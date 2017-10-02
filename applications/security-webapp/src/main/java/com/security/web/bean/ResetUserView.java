/**
 * 
 */
package com.security.web.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 12:48:29
 */
public class ResetUserView{
	@NotEmpty
	@Size(min=8)
	private String userName;
	@NotEmpty
	@Size(min=8)
	private String password;
	@NotEmpty
	@Size(min=8)
	private String newPasswordAgain;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the newPasswordAgain
	 */
	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}
	/**
	 * @param newPasswordAgain the newPasswordAgain to set
	 */
	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
