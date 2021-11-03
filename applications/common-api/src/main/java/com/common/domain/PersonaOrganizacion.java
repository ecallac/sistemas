/**
 * 
 */
package com.common.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "personaorganizacion")
public class PersonaOrganizacion {
	@EmbeddedId
	private PersonaOrganizacionKey id;
	
	@ManyToOne
	@MapsId("personaId")
	@JoinColumn(name="persona_Id")
	private Persona persona;
	
	@ManyToOne
	@MapsId("organizacionId")
	@JoinColumn(name="organizacion_id")
	private Organizacion organizacion;

	public PersonaOrganizacionKey getId() {
		return id;
	}

	public void setId(PersonaOrganizacionKey id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "PersonaOrganizacion [id=" + id + ", persona=" + persona + ", organizacion=" + organizacion + "]";
	}
	
	
}
