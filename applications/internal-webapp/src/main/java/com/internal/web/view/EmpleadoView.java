/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpleadoView extends BaseEntity {
	@NotEmpty
	private String codigo;
	@NotEmpty
	private String estado;
	private String estadoDescripcion;
	private String estadoType;
	private String ids[];

	private PersonaView persona;
	@NotEmpty
	private Date fechaingreso;
	@NotEmpty
	private String tipotitulo;
	private String apreciacion;
	@NotEmpty
	private String cargofamiliar;
	private EmpleadoView reporta;
	private EmpleadoView supervisorausencias;

}
