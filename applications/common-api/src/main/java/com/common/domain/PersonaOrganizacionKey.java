/**
 * 
 */
package com.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author efrain.calla
 *
 */
@Embeddable
public class PersonaOrganizacionKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="persona_id")
	private Long personaId;
	
	@Column(name="organizacion_id")
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
