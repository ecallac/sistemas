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
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReglaDetalle extends BaseEntity {
	private String condicion;
	private Double valornumero;
	private String valorcadena;
	private String activo;
	private Regla regla;

}
