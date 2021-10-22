/**
 * 
 */
package com.security;

import java.io.Serializable;

/**
 * @author efrain.calla
 *
 */
public class RolePermissionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;
	private Long permissionId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	
	
}
