/**
 * 
 */
package com.security.web.bean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
public class RoleView {
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	private String enabled;
	
	public String getIdString() {
		return String.valueOf(id);
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
