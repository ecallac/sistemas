/**
 * 
 */
package com.security;

/**
 * @author efrain.calla
 *
 */
public class RolePermission {

	private RolePermissionKey id;
	private Role role;
	private Permission permission;

	public RolePermissionKey getId() {
		return id;
	}

	public void setId(RolePermissionKey id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	
	
}
