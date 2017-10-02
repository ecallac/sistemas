/**
 * 
 */
package com.security.domain.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author EFRAIN
 * @dateCreated 15 may. 2017 21:48:52
 */
@Entity
@Table(name = "roleuserview")
public class RoleUserView {
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String role_status;
	@Column
	private Long role_id;
	@Column
	private Long user_id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String user_status;
	@Column
	private Long entidadrole_id;
	@Column
	private String question;
	@Column
	private String answer;
	@Column
	private Date activationdate;
	@Column
	private Date inactivationdate;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRoleNameWithPrefix(){
		return "ROLE_"+name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the role_status
	 */
	public String getRole_status() {
		return role_status;
	}
	/**
	 * @param role_status the role_status to set
	 */
	public void setRole_status(String role_status) {
		this.role_status = role_status;
	}
	/**
	 * @return the role_id
	 */
	public Long getRole_id() {
		return role_id;
	}
	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
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
	 * @return the user_status
	 */
	public String getUser_status() {
		return user_status;
	}
	/**
	 * @param user_status the user_status to set
	 */
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	/**
	 * @return the entidadrole_id
	 */
	public Long getEntidadrole_id() {
		return entidadrole_id;
	}
	/**
	 * @param entidadrole_id the entidadrole_id to set
	 */
	public void setEntidadrole_id(Long entidadrole_id) {
		this.entidadrole_id = entidadrole_id;
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
	/**
	 * @return the activationdate
	 */
	public Date getActivationdate() {
		return activationdate;
	}
	/**
	 * @param activationdate the activationdate to set
	 */
	public void setActivationdate(Date activationdate) {
		this.activationdate = activationdate;
	}
	/**
	 * @return the inactivationdate
	 */
	public Date getInactivationdate() {
		return inactivationdate;
	}
	/**
	 * @param inactivationdate the inactivationdate to set
	 */
	public void setInactivationdate(Date inactivationdate) {
		this.inactivationdate = inactivationdate;
	}
	
	
}
