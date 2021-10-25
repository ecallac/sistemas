/**
 * 
 */
package com.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author efrain.calla
 *
 */
@Embeddable
public class RoleUserKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="user_id")
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

	@Override
	public String toString() {
		return "RoleUserKey [roleId=" + roleId + ", userId=" + userId + "]";
	}
	
}
