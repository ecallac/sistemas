/**
 * 
 */
package com.security.client.bean;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class ModuleBean {
	private Long id;
	private String name;
	private String description;
	private String enabled;
	private String author;
	private String moduleVersion;
	private String updatedBy;
	private String createdBy;
	private List<PermissionBean> permissionList;
	
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getModuleVersion() {
		return moduleVersion;
	}
	public void setModuleVersion(String moduleVersion) {
		this.moduleVersion = moduleVersion;
	}
	public List<PermissionBean> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<PermissionBean> permissionList) {
		this.permissionList = permissionList;
	}
	
}
