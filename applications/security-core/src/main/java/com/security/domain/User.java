/**
 * 
 */
package com.security.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity{
	
	@NotEmpty
	@Size(min=4)
	@Column(name = "username", unique = true, nullable = false)
	private String userName;
	
	@NotEmpty
	@Size(min=4)
	@Column(name = "password",nullable = false)
	private String password;
	
	@NotEmpty
	@Column(name = "status",nullable = false)
	private String status;
	
	@Column(name = "entidadrole_Id")
	private Long entidadRoleId;
	
	@NotEmpty
	@Column(name = "question",nullable = false)
	private String question;
	
	@NotEmpty
	@Column(name = "answer",nullable = false)
	private String answer;
	@Column
	private Date activationDate;
	@Column
	private Date inactivationDate;
	
//	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
//	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
	@Cascade({CascadeType.SAVE_UPDATE})
	private List<Role> roles;
	
	@OneToMany(mappedBy="user",fetch=FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Session> sessions;
	
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
//	public List<RoleUser> getRolUsers() {
//		return rolUsers;
//	}
//	public void setRolUsers(List<RoleUser> rolUsers) {
//		this.rolUsers = rolUsers;
//	}
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
	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
