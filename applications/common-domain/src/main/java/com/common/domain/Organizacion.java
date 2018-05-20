/**
 * 
 */
package com.common.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "organizacion")
public class Organizacion extends Entidad {
	@Column(name = "tipo_organizacion")
	private String tipoOrganizacion;
	private String numeroidentificacion;
	private String razonsocial;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	private Entidad entidad;
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "personaorganizacion", joinColumns = {
			@JoinColumn(name = "organizacion_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "persona_id",
					nullable = false, updatable = false) })
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
