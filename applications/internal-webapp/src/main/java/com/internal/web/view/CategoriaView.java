/**
 *
 */
package com.internal.web.view;

import com.BaseEntity;
import com.common.Categoria;
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
public class CategoriaView extends BaseEntity {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String tipocategoria;
	@NotEmpty
	private String tipoestrategiaretiro;
	private CategoriaView categoriapadre;
	private String categoriapadreId;
	@NotEmpty
	private String status;
	private String statusDescripcion;
	private String statusType;
	private String ids[];


}
