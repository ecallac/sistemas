/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import com.common.Direccion;
import com.common.Organizacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
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
public class SucursalView extends BaseEntity {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String estado;
	private String estadoDescripcion;
	private String estadoType;
	private String ids[];
	private OrganizacionView organizacion;
	@NotEmpty
	private String organizacionId;
	private String tiposucursal;
	@NotEmpty
	private String nombrecorto;
	private DireccionView direccion;
	@NotEmpty
	private String direccionId;

}
