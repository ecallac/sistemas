/**
 * 
 */
package com.security;

import java.util.List;

import com.BaseEntity;


/**
 * @author efrain.calla
 *
 */
public class Module extends BaseEntity{
	private String name;
	private String description;
	private String enabled;
	private String author;
	private String moduleVersion;
	private List<Permission> permissions;

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
	
	

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the moduleVersion
	 */
	public String getModuleVersion() {
		return moduleVersion;
	}

	/**
	 * @param moduleVersion the moduleVersion to set
	 */
	public void setModuleVersion(String moduleVersion) {
		this.moduleVersion = moduleVersion;
	}
	
	
}
