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
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	
	@Column(name = "status",nullable = false)
	private String status;
	
//	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
//	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roleuser", joinColumns = {
			@JoinColumn(name = "role_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "user_id",
					nullable = false, updatable = false) })
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<User> users;
	
//	@OneToMany(mappedBy="role",fetch=FetchType.LAZY)
//	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rolepermission", joinColumns = {
			@JoinColumn(name = "role_id", nullable = false, updatable = false) },
			inverseJoinColumns = { 
					@JoinColumn(name = "permission_id", nullable = false, updatable = false) })
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<Permission> permissions;
	
	public String getNameWithPrefix(){
		return "ROLE_"+name;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
}
