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
public class EntidadRolAtributo extends BaseEntity {
	private String status;
	private String tipoAtributo;
	private String valor;
	private EntidadRol entidadRol;

}
