/**
 * 
 */
package com.security.web.bean;

/**
 * @author efrain.calla
 *
 */
public class UserRoleView {
//	private UserView userView;
//	private RoleView roleView;
	private Long roleId;
	private Long userId;
	private String roleDescription;
	private String selected;
	
//	public UserView getUserView() {
//		return userView;
//	}
//	public void setUserView(UserView userView) {
//		this.userView = userView;
//	}
//	public RoleView getRoleView() {
//		return roleView;
//	}
//	public void setRoleView(RoleView roleView) {
//		this.roleView = roleView;
//	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	
}
