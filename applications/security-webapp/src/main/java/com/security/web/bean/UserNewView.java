/**
 * 
 */
package com.security.web.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
public class UserNewView extends UserEditView {
	
	@NotEmpty
	@Size(min=8)
	private String password;
	@NotEmpty
	@Size(min=8)
	private String passwordAgain;
	
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
	
	
	
}
