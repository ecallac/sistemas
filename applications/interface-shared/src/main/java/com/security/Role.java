/**
 * 
 */
package com.security;

import java.util.List;

import com.BaseEntity;

/**
 * @author efrain.calla
 *
 */
public class Role extends BaseEntity{
	private String name;
	private String description;
	private String enabled;
	private List<User> users;
	private List<Permission> permissions;
	private List<RoleUser> roleUsers;
	public String getNameWithPrefix(){
		return "ROLE_"+name;
	}
	
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
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	
}
