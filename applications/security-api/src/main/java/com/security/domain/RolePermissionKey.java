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
public class RolePermissionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="permission_id")
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

	@Override
	public String toString() {
		return "RolePermissionKey [roleId=" + roleId + ", permissionId=" + permissionId + "]";
	}
	
	
}
