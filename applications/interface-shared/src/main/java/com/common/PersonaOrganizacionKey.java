/**
 * 
 */
package com.common;

import java.io.Serializable;

/**
 * @author efrain.calla
 *
 */
public class PersonaOrganizacionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long personaId;
	private Long organizacionId;

	public Long getPersonaId() {
		return personaId;
	}

	public void setPersonaId(Long personaId) {
		this.personaId = personaId;
	}

	public Long getOrganizacionId() {
		return organizacionId;
	}

	public void setOrganizacionId(Long organizacionId) {
		this.organizacionId = organizacionId;
	}

	@Override
	public String toString() {
		return "PersonaOrganizacionKey [personaId=" + personaId + ", organizacionId=" + organizacionId + "]";
	}
	

}
