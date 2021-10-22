/**
 * 
 */
package com.security.web.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.security.web.constraint.ValidDate;


/**
 * @author efrain.calla
 *
 */
public class PersonaView {
	public static String FORMAT="yyyy/MM/dd";
	@NotEmpty
	private String tipoEntidadId;
	@NotEmpty
	private String tipoDocumentoIdentificaion;
	@NotEmpty
	private String numeroidentificacion;
	private String nombres;
	private String apellidos;
	private String tipoEstadoCivil;
	private String sexo;
	@ValidDate(pattern="yyyy/MM/dd",message="Is invalid date")
	private String fechanacimiento;
	@Email
	private String email;
	
	
	public String getTipoEntidadId() {
		return tipoEntidadId;
	}
	public void setTipoEntidadId(String tipoEntidadId) {
		this.tipoEntidadId = tipoEntidadId;
	}
	public String getTipoDocumentoIdentificaion() {
		return tipoDocumentoIdentificaion;
	}
	public void setTipoDocumentoIdentificaion(String tipoDocumentoIdentificaion) {
		this.tipoDocumentoIdentificaion = tipoDocumentoIdentificaion;
	}
	public String getNumeroidentificacion() {
		return numeroidentificacion;
	}
	public void setNumeroidentificacion(String numeroidentificacion) {
		this.numeroidentificacion = numeroidentificacion;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTipoEstadoCivil() {
		return tipoEstadoCivil;
	}
	public void setTipoEstadoCivil(String tipoEstadoCivil) {
		this.tipoEstadoCivil = tipoEstadoCivil;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
