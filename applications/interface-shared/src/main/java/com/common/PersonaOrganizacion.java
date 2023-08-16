/**
 * 
 */
package com.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonaOrganizacion {
	private PersonaOrganizacionKey id;
	
	private Persona persona;
	private Organizacion organizacion;

}
