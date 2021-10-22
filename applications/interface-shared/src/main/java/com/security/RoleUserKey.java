/**
 * 
 */
package com.security;

import java.io.Serializable;

/**
 * @author efrain.calla
 *
 */
public class RoleUserKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;
	private Long userId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
