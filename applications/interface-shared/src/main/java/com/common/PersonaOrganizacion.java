/**
 * 
 */
package com.common;

/**
 * @author efrain.calla
 *
 */
public class PersonaOrganizacion {
	private PersonaOrganizacionKey id;
	
	private Persona persona;
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
