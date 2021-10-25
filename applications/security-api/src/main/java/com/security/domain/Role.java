/**
 * 
 */
package com.security.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity{
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "enabled",nullable = false)
	private String enabled;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "roleuser", joinColumns = {
//			@JoinColumn(name = "role_id", nullable = false, updatable = false) },
//			inverseJoinColumns = { @JoinColumn(name = "user_id",
//					nullable = false, updatable = false) })
//	@Cascade({CascadeType.SAVE_UPDATE})
//	@Fetch(value = FetchMode.SUBSELECT)
	transient
	private List<User> users;
	
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "rolepermission", joinColumns = {
//			@JoinColumn(name = "role_id", nullable = false, updatable = false) },
//			inverseJoinColumns = { 
//					@JoinColumn(name = "permission_id", nullable = false, updatable = false) })
//	@Cascade({CascadeType.SAVE_UPDATE})
	transient
	private List<Permission> permissions;
	
//	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
	transient
	private List<RoleUser> roleUsers;
	
	public List<RoleUser> getRoleUsers() {
		return roleUsers;
	}
	public void setRoleUsers(List<RoleUser> roleUsers) {
		this.roleUsers = roleUsers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
//	public List<RoleUser> getRolUsers() {
//		return rolUsers;
//	}
//	public void setRolUsers(List<RoleUser> rolUsers) {
//		this.rolUsers = rolUsers;
//	}
//	/**
//	 * @return the rolePermissions
//	 */
//	public List<RolePermission> getRolePermissions() {
//		return rolePermissions;
//	}
//	/**
//	 * @param rolePermissions the rolePermissions to set
//	 */
//	public void setRolePermissions(List<RolePermission> rolePermissions) {
//		this.rolePermissions = rolePermissions;
//	}
//	

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description + ", enabled=" + enabled + ", users=" + users
				+ ", permissions=" + permissions + ", roleUsers=" + roleUsers + "]";
	}
	
}
