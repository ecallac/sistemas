/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import lombok.*;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AreaView extends BaseEntity {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String activo;
	private String activoDescripcion;
	private String activoType;
	private String ids[];
	private AreaView parentArea;
	private String parentAreaId;
	private List<AreaView> childAreas;

}
