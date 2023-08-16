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
public class Regla extends BaseEntity {
	private String categoria;
	private String nombre;
	private String codigo;
	private String descripcion;
	private String tipoValor;
	private String activo;
	private List<ReglaDetalle> reglaDetalles;

}
