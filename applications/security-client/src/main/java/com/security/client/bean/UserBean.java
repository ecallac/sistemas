/**
 * 
 */
package com.security.client.bean;

import java.util.Date;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class UserBean {
	private Long id;
	private String userName;
	private String password;
	private String newPassword;
	private String newPasswordAgain;
	private String name;
	private String question;
	private String answer;
	private Long entidadRoleId;
	private String status;
	private Date activationDate;
	private Date inactivationDate;
	private String updatedBy;
	private String createdBy;
	private List<RoleBean> roleList;
	private List<PermissionBean> permissionList;
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getEntidadRoleId() {
		return entidadRoleId;
	}
	public void setEntidadRoleId(Long entidadRoleId) {
		this.entidadRoleId = entidadRoleId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	public Date getInactivationDate() {
		return inactivationDate;
	}
	public void setInactivationDate(Date inactivationDate) {
		this.inactivationDate = inactivationDate;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public List<RoleBean> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}
	public List<PermissionBean> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<PermissionBean> permissionList) {
		this.permissionList = permissionList;
	}
	
}
