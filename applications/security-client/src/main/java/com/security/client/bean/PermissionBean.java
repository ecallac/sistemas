/**
 * 
 */
package com.security.client.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
