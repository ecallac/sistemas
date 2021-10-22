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
@Table(name = "rolepermission")
public class RolePermission {

	@EmbeddedId
	private RolePermissionKey id;
	
	@ManyToOne
	@MapsId("roleId")
	@JoinColumn(name="role_Id")
	private Role role;
	
	@ManyToOne
	@MapsId("permissionId")
	@JoinColumn(name="permission_id")
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
