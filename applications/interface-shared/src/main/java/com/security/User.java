/**
 * 
 */
package com.security;

import java.util.Date;
import java.util.List;

import com.BaseEntity;
/**
 * @author efrain.calla
 *
 */
public class User extends BaseEntity{
	private String userName;
	private String password;
	private String status;
	private Long entidadRoleId;
	private String question;
	private String answer;
	private Date activationDate;
	private Date inactivationDate;
	private List<Role> roles;
	private List<Session> sessions;
	private List<RoleUser> roleUsers;
	
	public List<RoleUser> getRoleUsers() {
		return roleUsers;
	}
	public void setRoleUsers(List<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	/**
	 * @return the entidadRoleId
	 */
	public Long getEntidadRoleId() {
		return entidadRoleId;
	}
	/**
	 * @param entidadRoleId the entidadRoleId to set
	 */
	public void setEntidadRoleId(Long entidadRoleId) {
		this.entidadRoleId = entidadRoleId;
	}
	/**
	 * @return the activationDate
	 */
	public Date getActivationDate() {
		return activationDate;
	}
	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	/**
	 * @return the inactivationDate
	 */
	public Date getInactivationDate() {
		return inactivationDate;
	}
	/**
	 * @param inactivationDate the inactivationDate to set
	 */
	public void setInactivationDate(Date inactivationDate) {
		this.inactivationDate = inactivationDate;
	}
	/**
	 * @return the sessions
	 */
	public List<Session> getSessions() {
		return sessions;
	}
	/**
	 * @param sessions the sessions to set
	 */
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	
	
}
