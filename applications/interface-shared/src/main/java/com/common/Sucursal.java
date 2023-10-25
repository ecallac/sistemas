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
public class Sucursal extends BaseEntity {
	private String nombre;
	private String estado;
	private Organizacion organizacion;
	private String tiposucursal;
	private String nombrecorto;
	private Direccion direccion;
}
