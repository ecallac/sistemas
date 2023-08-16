/**
 * 
 */
package com.common;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cargo extends BaseEntity {

	private String nombre;
	private String activo;
	private BigDecimal salariomin;
	private BigDecimal salariomax;
	private Cargo parentCargo;
	transient
	private List<Cargo> childCargos;

}
