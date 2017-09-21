/**
 * 
 */
package com.security.domain.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author EFRAIN
 * @dateCreated 15 may. 2017 21:50:05
 */
@Entity
@Table(name = "rolepermissionview")
public class RolePermissionView {
	@Column
	private Long module_id;
	@Column
	private String permission_name;
	@Column
	private String permission_description;
	@Column
	private String path;
	@Column
	private String permission_status;
	@Column
	private Long parent_permission_id;
	@Column
	private Long permission_id;
	@Column
	private Long role_id;
	@Column
	private Boolean read;
	@Column
	private Boolean write;
	@Column
	private Boolean update;
	@Column
	private Boolean delete;
	@Column
	private Boolean execute;
	@Column
	private String role_name;
	@Column
	private String role_description;
	@Column
	private String role_status;
	
	/**
	 * @return the module_id
	 */
	public Long getModule_id() {
		return module_id;
	}
	/**
	 * @param module_id the module_id to set
	 */
	public void setModule_id(Long module_id) {
		this.module_id = module_id;
	}
	/**
	 * @return the permission_name
	 */
	public String getPermission_name() {
		return permission_name;
	}
	/**
	 * @param permission_name the permission_name to set
	 */
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	/**
	 * @return the permission_description
	 */
	public String getPermission_description() {
		return permission_description;
	}
	/**
	 * @param permission_description the permission_description to set
	 */
	public void setPermission_description(String permission_description) {
		this.permission_description = permission_description;
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
	/**
	 * @return the permission_status
	 */
	public String getPermission_status() {
		return permission_status;
	}
	/**
	 * @param permission_status the permission_status to set
	 */
	public void setPermission_status(String permission_status) {
		this.permission_status = permission_status;
	}
	/**
	 * @return the parent_permission_id
	 */
	public Long getParent_permission_id() {
		return parent_permission_id;
	}
	/**
	 * @param parent_permission_id the parent_permission_id to set
	 */
	public void setParent_permission_id(Long parent_permission_id) {
		this.parent_permission_id = parent_permission_id;
	}
	/**
	 * @return the permission_id
	 */
	public Long getPermission_id() {
		return permission_id;
	}
	/**
	 * @param permission_id the permission_id to set
	 */
	public void setPermission_id(Long permission_id) {
		this.permission_id = permission_id;
	}
	/**
	 * @return the role_id
	 */
	public Long getRole_id() {
		return role_id;
	}
	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	/**
	 * @return the read
	 */
	public Boolean getRead() {
		return read;
	}
	/**
	 * @param read the read to set
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}
	/**
	 * @return the write
	 */
	public Boolean getWrite() {
		return write;
	}
	/**
	 * @param write the write to set
	 */
	public void setWrite(Boolean write) {
		this.write = write;
	}
	/**
	 * @return the update
	 */
	public Boolean getUpdate() {
		return update;
	}
	/**
	 * @param update the update to set
	 */
	public void setUpdate(Boolean update) {
		this.update = update;
	}
	/**
	 * @return the delete
	 */
	public Boolean getDelete() {
		return delete;
	}
	/**
	 * @param delete the delete to set
	 */
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return the execute
	 */
	public Boolean getExecute() {
		return execute;
	}
	/**
	 * @param execute the execute to set
	 */
	public void setExecute(Boolean execute) {
		this.execute = execute;
	}
	/**
	 * @return the role_name
	 */
	public String getRole_name() {
		return role_name;
	}
	/**
	 * @param role_name the role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	/**
	 * @return the role_description
	 */
	public String getRole_description() {
		return role_description;
	}
	/**
	 * @param role_description the role_description to set
	 */
	public void setRole_description(String role_description) {
		this.role_description = role_description;
	}
	/**
	 * @return the role_status
	 */
	public String getRole_status() {
		return role_status;
	}
	/**
	 * @param role_status the role_status to set
	 */
	public void setRole_status(String role_status) {
		this.role_status = role_status;
	}
	
	
}
