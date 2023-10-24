/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import com.common.Entidad;
import com.common.Organizacion;
import com.internal.web.constraint.ValidDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonaView extends BaseEntity {
	public static String FORMAT="yyyy/MM/dd";
	@NotEmpty
	private String tipoDocumentoIdentificaion;
	private String tipoDocumentoIdentificaionDescripcion;
	@NotEmpty
	private String numeroidentificacion;
	@NotEmpty
	private String nombres;
	private String apellidos;
	@NotEmpty
	private String tipoEstadoCivil;
	private String tipoEstadoCivilDescripcion;
	@NotEmpty
	private String sexo;
	@ValidDate(pattern="yyyy/MM/dd",message="Is invalid date")
	private String fechanacimiento;
	@Email
	private String email;
	private String fullName;
	@NotEmpty
	private String status;
	private String statusDescripcion;
	private String statusType;
	private String ids[];
}