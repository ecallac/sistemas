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
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 19:28:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoBase extends BaseEntity {
	private String categoria;
	private String codigo;
	private String descripcion;
	private String activo;

}
