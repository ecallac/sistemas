/**
 * 
 */
package com.common.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "entidad")
public class Entidad extends BaseEntity{
	@Column(name = "tipo_entidad")
	private String tipoEntidad;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
//	transient
	@JsonIgnore
	private Persona persona;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
//	transient
	@JsonIgnore
	private Organizacion organizacion;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SELECT)
	transient
	private List<EntidadRol> entidadRols;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "entidad", fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SELECT)
	transient
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
