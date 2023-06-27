package com.internal.web.view;

import javax.validation.constraints.Size;

public class PasswordView {
	private Long userId;
	@Size(min=4,max=255)
	private String oldPassword;
	@Size(min=4,max=255)
	private String newPassword;
	@Size(min=4,max=255)
	private String newPasswordAgain;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}
	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}
	
}
