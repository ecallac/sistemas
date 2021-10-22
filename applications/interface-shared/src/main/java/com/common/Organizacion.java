/**
 * 
 */
package com.common;

import java.util.List;

import com.BaseEntity;

/**
 * @author efrain
 *
 */
public class Organizacion extends BaseEntity {
	private String tipoOrganizacion;
	private String numeroidentificacion;
	private String razonsocial;
	private Entidad entidad;
	private List<Persona> personas;

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

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	
	
}
