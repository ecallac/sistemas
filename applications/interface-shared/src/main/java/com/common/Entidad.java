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
public class Entidad extends BaseEntity{
	private String tipoEntidad;
	private Persona persona;
	private Organizacion organizacion;
	private List<EntidadRol> entidadRols;
	private List<Telefono> telefonos;
	
	public String getTipoEntidad() {
		return tipoEntidad;
	}
	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	public List<EntidadRol> getEntidadRols() {
		return entidadRols;
	}
	public void setEntidadRols(List<EntidadRol> entidadRols) {
		this.entidadRols = entidadRols;
	}
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	
	
}
