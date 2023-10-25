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
public class AreaSucursal extends BaseEntity {
	private Sucursal sucursal;
	private Area area;
	private Empleado responsable;
	private String estado;
}
