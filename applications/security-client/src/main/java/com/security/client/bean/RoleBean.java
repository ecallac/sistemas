/**
 * 
 */
package com.security.client.bean;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class RoleBean {
	private Long id;
	private String name;
	private String description;
	private String enabled;
	private String updatedBy;
	private String createdBy;
	private List<UserBean> userList;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBean> userList) {
		this.userList = userList;
	}

	public List<PermissionBean> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<PermissionBean> permissionList) {
		this.permissionList = permissionList;
	}
	
}
