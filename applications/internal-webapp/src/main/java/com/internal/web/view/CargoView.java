/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import lombok.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

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
public class CargoView extends BaseEntity {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String activo;
	private String activoDescripcion;
	private String activoType;
	private String ids[];
	@NotEmpty
	private BigDecimal salariomin;
	@NotEmpty
	private BigDecimal salariomax;
	private CargoView parentCargo;
	private String parentCargoId;
	transient
	private List<CargoView> childCargos;

}
