/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import com.common.Entidad;
import com.common.Persona;
import com.internal.web.constraint.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrganizacionView extends BaseEntity {
	public static String FORMAT="yyyy/MM/dd";
	@NotEmpty
	private String tipoOrganizacion;
	private String tipoOrganizacionDescripcion;
	@NotEmpty
	private String numeroidentificacion;
	@NotEmpty
	private String razonsocial;
	private String logo;
	private String url;
	@NotEmpty
	private String status;
	private String statusDescripcion;
	private String statusType;
	private String ids[];
}