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
public class UserNewView {
	private String entidadId;
	@NotEmpty
	private String fullName;
//	@NotEmpty
	@Size(min=4,max=255)
	private String userName;
//	@NotEmpty
	@Size(min=4,max=255)
	private String password;
//	@NotEmpty
	@Size(min=4,max=255)
	private String passwordAgain;
	private String status;
	private Long entidadRoleId;
	@NotEmpty
	private String question;
	@NotEmpty
	private String answer;
	
	
	public String getEntidadId() {
		return entidadId;
	}
	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getEntidadRoleId() {
		return entidadRoleId;
	}
	public void setEntidadRoleId(Long entidadRoleId) {
		this.entidadRoleId = entidadRoleId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
