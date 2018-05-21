/**
 * 
 */
package com.security.web.bean;

import java.util.Date;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class UserView extends UserEditView {
	
	private Date activationDate;
	private Date inactivationDate;
	
//	private List<RoleView> roles;
	
	public Date getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}
	public Date getInactivationDate() {
		return inactivationDate;
	}
	public void setInactivationDate(Date inactivationDate) {
		this.inactivationDate = inactivationDate;
	}
//	public List<RoleView> getRoles() {
//		return roles;
//	}
//	public void setRoles(List<RoleView> roles) {
//		this.roles = roles;
//	}
	
	
}
