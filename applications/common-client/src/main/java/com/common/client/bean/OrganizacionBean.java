/**
 * 
 */
package com.common.client.bean;

/**
 * @author efrain
 *
 */
public class OrganizacionBean {
	private Long entidadId;
	private Long id;
	private String tipoOrganizacion;
	private String numeroidentificacion;
	private String razonsocial;
	public Long getEntidadId() {
		return entidadId;
	}
	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoOrganizacion() {
		return tipoOrganizacion;
	}
	public void setTipoOrganizacion(String tipoOrganizacion) {
		this.tipoOrganizacion = tipoOrganizacion;
	}
	public String getNumeroidentificacion() {
		return numeroidentificacion;
	}
	public void setNumeroidentificacion(String numeroidentificacion) {
		this.numeroidentificacion = numeroidentificacion;
	}
	public String getRazonsocial() {
		return razonsocial;
	}
	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}
	
}
