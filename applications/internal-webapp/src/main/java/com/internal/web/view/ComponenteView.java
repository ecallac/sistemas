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
public class ComponenteView extends BaseEntity {
	@NotEmpty
	private String nombre;
	private String descripcion;
	@NotEmpty
	private String status;
	private String statusDescripcion;
	private String statusType;
	private String ids[];
	@NotEmpty
	private String tipoComponente;
	private String tipoComponenteDescripcion;
}