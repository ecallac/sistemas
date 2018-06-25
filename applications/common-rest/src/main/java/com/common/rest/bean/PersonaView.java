/**
 * 
 */
package com.common.rest.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.common.rest.constraint.ValidDate;

/**
 * @author efrain.calla
 *
 */
public class PersonaView {
	public static String FORMAT="yyyy-MM-dd";
	@NotEmpty
	private String tipoDocumentoIdentificaion;
	@NotEmpty
	private String numeroidentificacion;
	@NotEmpty
	private String nombres;
	@NotEmpty
	private String apellidos;
	@NotEmpty
	private String tipoEstadoCivil;
	@NotEmpty
	private String sexo;
	@ValidDate(pattern="yyyy-MM-dd",message="Is invalid date")
	private String fechanacimiento;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String entidadId;
	@NotEmpty
	private String createdBy;
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
	public String getEntidadId() {
		return entidadId;
	}
	public void setEntidadId(String entidadId) {
		this.entidadId = entidadId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
