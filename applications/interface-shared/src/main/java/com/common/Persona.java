/**
 * 
 */
package com.common;

import java.util.Date;
import java.util.List;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Persona extends BaseEntity {
	private String tipoDocumentoIdentificaion;
	private String numeroidentificacion;
	private String nombres;
	private String apellidos;
	private String tipoEstadoCivil;
	private String sexo;
	private Date fechanacimiento;
	private String email;
	private Entidad entidad;
	private List<Organizacion> organizacions;
	private String fullName;

}
