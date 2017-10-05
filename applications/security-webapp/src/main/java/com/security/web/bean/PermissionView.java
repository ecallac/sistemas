/**
 * 
 */
package com.security.web.bean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain
 *
 */
public class PermissionView {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	
	private String path;
	
	private String enabled;
	
	private ModuleView module;
	
	private PermissionView parentPermission;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModuleView getModule() {
		return module;
	}

	public void setModule(ModuleView module) {
		this.module = module;
	}

	public PermissionView getParentPermission() {
		return parentPermission;
	}

	public void setParentPermission(PermissionView parentPermission) {
		this.parentPermission = parentPermission;
	}

	
}
