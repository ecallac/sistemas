/**
 * 
 */
package com.common;

import com.BaseEntity;
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
public class Direccion extends BaseEntity {
	private String direccionexacta;
	private String codigopostal;
	private Entidad entidad;
	private Ubigeo ubigeo;
	private String esprincipal;
	private String estado;

}
