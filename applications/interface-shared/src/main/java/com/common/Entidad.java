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
public class Entidad extends BaseEntity{
	private String tipoEntidad;
	private Persona persona;
	private Organizacion organizacion;
	private List<EntidadRol> entidadRols;
	private List<Telefono> telefonos;

}
