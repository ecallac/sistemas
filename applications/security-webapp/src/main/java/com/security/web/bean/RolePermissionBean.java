/**
 * 
 */
package com.security.web.bean;

import com.security.domain.Role;

/**
 * @author EFRAIN
 * @dateCreated 12 mar. 2017 18:16:24
 */
public class RolePermissionBean {
	private Role role;
	private String permission;
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	/**
	 * @param permission the permission to set
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	
}
