/**
 * 
 */
package com.security;

/**
 * @author efrain.calla
 *
 */
public class RoleUser {
	private RoleUserKey id;
	private Role role;
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
