/**
 * 
 */
package com.security.client.bean;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class PermissionBean {
	private Long id;
	private String name;
	private String path;
	private String description;
	private PermissionBean parentPermission;
	private String enabled;
	private String updatedBy;
	private String createdBy;
	private ModuleBean module;
	private List<RoleBean> roleList;
	

	public ModuleBean getModule() {
		return module;
	}

	public void setModule(ModuleBean module) {
		this.module = module;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<RoleBean> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleBean> roleList) {
		this.roleList = roleList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PermissionBean getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(PermissionBean parentPermission) {
		this.parentPermission = parentPermission;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
