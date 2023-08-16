/**
 * 
 */
package com.common;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ubigeo extends BaseEntity {
	private String codigo;
	private String descripcion;
	private String abreviatura;
	private Ubigeo parentUbigeo;
	private String estado;
	private String tipoubigeo;
	private List<Ubigeo> childubigeos;
	private List<Direccion> direccions;

}
