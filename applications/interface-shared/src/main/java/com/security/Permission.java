/**
 * 
 */
package com.security;

import java.util.List;

import com.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author EFRAIN
 * @dateCreated 24 mar. 2017 17:17:06
 */
public class Permission extends BaseEntity {
	private String name;
	private String description;
	private String path;
	private String enabled;
	private Module module;
	private Permission parentPermission;
	private List<Permission> childPermissions;
	private List<Role> roles;
	private String type;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	/**
	 * @return the parentPermission
	 */
	public Permission getParentPermission() {
		return parentPermission;
	}

	/**
	 * @param parentPermission the parentPermission to set
	 */
	public void setParentPermission(Permission parentPermission) {
		this.parentPermission = parentPermission;
	}

	public List<Permission> getChildPermissions() {
		return childPermissions;
	}

	public void setChildPermissions(List<Permission> childPermissions) {
		this.childPermissions = childPermissions;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


//	/**
//	 * @return the rolePermissions
//	 */
//	public List<RolePermission> getRolePermissions() {
//		return rolePermissions;
//	}
//
//	/**
//	 * @param rolePermissions the rolePermissions to set
//	 */
//	public void setRolePermissions(List<RolePermission> rolePermissions) {
//		this.rolePermissions = rolePermissions;
//	}
	
	
}
