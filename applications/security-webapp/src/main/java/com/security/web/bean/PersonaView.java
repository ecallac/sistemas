/**
 * 
 */
package com.security.web.bean;

import java.util.Date;

/**
 * @author efrain.calla
 *
 */
public class PersonaView {
	private String tipoDocumentoIdentificaion;
	private String numeroidentificacion;
	private String nombres;
	private String apellidos;
	private String tipoEstadoCivil;
	private String sexo;
	private Date fechanacimiento;
	private String email;
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
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
