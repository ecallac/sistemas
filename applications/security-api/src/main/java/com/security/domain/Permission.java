/**
 * 
 */
package com.security.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author EFRAIN
 * @dateCreated 24 mar. 2017 17:17:06
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "enabled",nullable = false)
	private String enabled;
	
	@ManyToOne
	@JoinColumn(name="module_Id")
	private Module module;
	
	@ManyToOne
	@JoinColumn(name="parent_Permission_Id")
	private Permission parentPermission;
	
//	@OneToMany(mappedBy="parentPermission",fetch=FetchType.EAGER)
//	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	transient
	private List<Permission> childPermissions;

//	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "permissions")
//	@Cascade({CascadeType.SAVE_UPDATE})
	transient
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

	/**
	 * @return the childPermissions
	 */
	public List<Permission> getChildPermissions() {
		return childPermissions;
	}

	/**
	 * @param childPermissions the childPermissions to set
	 */
	public void setChildPermissions(List<Permission> childPermissions) {
		this.childPermissions = childPermissions;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Permission{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", path='" + path + '\'' +
				", enabled='" + enabled + '\'' +
				", module=" + module +
				", parentPermission=" + parentPermission +
				", type='" + type + '\'' +
				'}';
	}
}
