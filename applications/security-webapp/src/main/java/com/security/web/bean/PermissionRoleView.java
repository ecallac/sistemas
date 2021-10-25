/**
 * 
 */
package com.security.web.bean;

/**
 * @author efrain.calla
 *
 */
public class PermissionRoleView {
	private Long roleId;
	private Long permissionId;
	private String moduleId;
	private String roleDescription;
	private String selected;
	private String permissionIds [];
    
    public String[] getPermissionIds() {
		return permissionIds;
	}


	public String getModuleId() {
		return moduleId;
	}


	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}
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
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
}
