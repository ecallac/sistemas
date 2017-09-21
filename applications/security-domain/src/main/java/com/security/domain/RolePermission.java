///**
// * 
// */
//package com.security.domain;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
///**
// * @author EFRAIN
// * @dateCreated 24 mar. 2017 17:34:15
// */
//@Entity
//@Table(name = "sec_role_permission")
//public class RolePermission {
//	
//	@ManyToOne
//	@JoinColumn(name="role_Id")
//	private Role role;
//	
//	@ManyToOne
//	@JoinColumn(name="permission_Id")
//	private Permission permission;
//	@Column
//	private Boolean read;
//	@Column
//	private Boolean write;
//	@Column
//	private Boolean update;
//	@Column
//	private Boolean delete;
//	@Column
//	private Boolean execute;
//	@Column
//	private Date dateCreated;
//	@Column
//	private Date dateUpdated;
//	@Column
//	private String createdBy;
//	@Column
//	private String updatedBy;
//
//	/**
//	 * @return the role
//	 */
//	public Role getRole() {
//		return role;
//	}
//
//	/**
//	 * @param role the role to set
//	 */
//	public void setRole(Role role) {
//		this.role = role;
//	}
//
//	/**
//	 * @return the permission
//	 */
//	public Permission getPermission() {
//		return permission;
//	}
//
//	/**
//	 * @param permission the permission to set
//	 */
//	public void setPermission(Permission permission) {
//		this.permission = permission;
//	}
//
//	/**
//	 * @return the read
//	 */
//	public Boolean getRead() {
//		return read;
//	}
//
//	/**
//	 * @param read the read to set
//	 */
//	public void setRead(Boolean read) {
//		this.read = read;
//	}
//
//	/**
//	 * @return the write
//	 */
//	public Boolean getWrite() {
//		return write;
//	}
//
//	/**
//	 * @param write the write to set
//	 */
//	public void setWrite(Boolean write) {
//		this.write = write;
//	}
//
//	/**
//	 * @return the update
//	 */
//	public Boolean getUpdate() {
//		return update;
//	}
//
//	/**
//	 * @param update the update to set
//	 */
//	public void setUpdate(Boolean update) {
//		this.update = update;
//	}
//
//	/**
//	 * @return the delete
//	 */
//	public Boolean getDelete() {
//		return delete;
//	}
//
//	/**
//	 * @param delete the delete to set
//	 */
//	public void setDelete(Boolean delete) {
//		this.delete = delete;
//	}
//
//	/**
//	 * @return the execute
//	 */
//	public Boolean getExecute() {
//		return execute;
//	}
//
//	/**
//	 * @param execute the execute to set
//	 */
//	public void setExecute(Boolean execute) {
//		this.execute = execute;
//	}
//
//	/**
//	 * @return the dateCreated
//	 */
//	public Date getDateCreated() {
//		return dateCreated;
//	}
//
//	/**
//	 * @param dateCreated the dateCreated to set
//	 */
//	public void setDateCreated(Date dateCreated) {
//		this.dateCreated = dateCreated;
//	}
//
//	/**
//	 * @return the dateUpdated
//	 */
//	public Date getDateUpdated() {
//		return dateUpdated;
//	}
//
//	/**
//	 * @param dateUpdated the dateUpdated to set
//	 */
//	public void setDateUpdated(Date dateUpdated) {
//		this.dateUpdated = dateUpdated;
//	}
//
//	/**
//	 * @return the createdBy
//	 */
//	public String getCreatedBy() {
//		return createdBy;
//	}
//
//	/**
//	 * @param createdBy the createdBy to set
//	 */
//	public void setCreatedBy(String createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	/**
//	 * @return the updatedBy
//	 */
//	public String getUpdatedBy() {
//		return updatedBy;
//	}
//
//	/**
//	 * @param updatedBy the updatedBy to set
//	 */
//	public void setUpdatedBy(String updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//	
//	
//}
