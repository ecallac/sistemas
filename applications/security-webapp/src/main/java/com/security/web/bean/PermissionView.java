/**
 * 
 */
package com.security.web.bean;

import org.apache.commons.lang.StringUtils;
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
	@NotEmpty
	private String path;
	
	private String enabled;
	
	private ModuleView module;
	@NotEmpty
	private String moduleId;
	
	private PermissionView parentPermission;
	
	private String parentPermissionId;
	
	

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		ModuleView view = new ModuleView();
		view.setId(Long.valueOf(moduleId));
		this.module = view;
		this.moduleId = moduleId;
	}

	public String getParentPermissionId() {
		return parentPermissionId;
	}

	public void setParentPermissionId(String parentPermissionId) {
		PermissionView view = new  PermissionView();
		view.setId(Long.valueOf(parentPermissionId));
		this.parentPermission = view;
		this.parentPermissionId = parentPermissionId;
	}

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
		if (this.module==null && StringUtils.isNotBlank(moduleId)) {
			ModuleView view = new ModuleView();
			view.setId(Long.valueOf(this.moduleId));
			this.module = view;
		}
		return module;
	}

	public void setModule(ModuleView module) {
		this.module = module;
	}

	public PermissionView getParentPermission() {
		if (this.parentPermission==null && StringUtils.isNotBlank(parentPermissionId)) {
			PermissionView view = new  PermissionView();
			view.setId(Long.valueOf(this.parentPermissionId));
			this.parentPermission = view;
		}
		return parentPermission;
	}

	public void setParentPermission(PermissionView parentPermission) {
		this.parentPermission = parentPermission;
	}

	
}
