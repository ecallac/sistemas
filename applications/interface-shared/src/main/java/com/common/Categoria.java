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
public class Categoria extends BaseEntity {
	private String nombre;
	private String tipocategoria;
	private String tipoestrategiaretiro;
	private Categoria categoriapadre;
	private String status;
}
