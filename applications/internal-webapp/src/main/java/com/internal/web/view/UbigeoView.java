/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import com.common.Ubigeo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UbigeoView extends BaseEntity {
	@NotEmpty
	private String codigo;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private String abreviatura;
	private UbigeoView parentUbigeo;
	private String parentUbigeoId;
	@NotEmpty
	private String tipoubigeo;
	@NotEmpty
	private String estado;
	private String estadoDescripcion;
	private String estadoType;
	private String ids[];

}
