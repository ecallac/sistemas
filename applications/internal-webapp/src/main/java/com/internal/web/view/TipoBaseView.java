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

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TipoBaseView extends BaseEntity {
	@NotEmpty
	private String categoria;
	@NotEmpty
	private String codigo;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private String activo;
	private String activoDescripcion;
	private String activoType;
	private String ids[];
}
