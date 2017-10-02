/**
 * 
 */
package com.security.web.bean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
public class ModuleView {
	private Long id;
	@NotEmpty(message="Enter Name.")
	private String name;
	@NotEmpty
	private String description;
	private String enabled;
	private String author;
	private String moduleVersion;
	
	public String getIdString() {
		return String.valueOf(id);
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getModuleVersion() {
		return moduleVersion;
	}
	public void setModuleVersion(String moduleVersion) {
		this.moduleVersion = moduleVersion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
