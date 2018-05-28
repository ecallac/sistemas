/**
 * 
 */
package com.common.client.bean;

/**
 * @author efrain.calla
 *
 */
public class EntidadRoleBean {
	private Long id;
	private String tipoEntidadRole;
	private Long entidadId;
	private String createdBy;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoEntidadRole() {
		return tipoEntidadRole;
	}
	public void setTipoEntidadRole(String tipoEntidadRole) {
		this.tipoEntidadRole = tipoEntidadRole;
	}
	public Long getEntidadId() {
		return entidadId;
	}
	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
