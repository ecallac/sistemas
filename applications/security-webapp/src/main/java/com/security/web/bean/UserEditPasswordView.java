/**
 * 
 */
package com.security.web.bean;

import javax.validation.constraints.Size;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 12:48:29
 */
public class UserEditPasswordView{

	private Long id;
	private String userName;
//	@NotEmpty
	@Size(min=4,max=255)
	private String password;
//	@NotEmpty
	@Size(min=4,max=255)
	private String passwordAgain;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordAgain() {
		return passwordAgain;
	}
	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
