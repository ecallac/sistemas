/**
 * 
 */
package com.common;

import java.util.List;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Organizacion extends BaseEntity {
	private String tipoOrganizacion;
	private String numeroidentificacion;
	private String razonsocial;
	private Entidad entidad;
	private List<Persona> personas;

}
