/**
 * 
 */
package com.security.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "roleuser")
public class RoleUser {
	@EmbeddedId
	private RoleUserKey id;
	
	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name="role_Id")
	private Role role;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;

	public RoleUserKey getId() {
		return id;
	}

	public void setId(RoleUserKey id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
