/**
 * 
 */
package com.common.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "persona")
public class Persona extends Entidad {
	@Column(name = "tipo_documento_identificaion")
	private String tipoDocumentoIdentificaion;
	private String numeroidentificacion;
	private String nombres;
	private String apellidos;
	@Column(name = "tipo_estado_civil")
	private String tipoEstadoCivil;
	private String sexo;
	private Date fechanacimiento;
	private String email;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "entidad_id")
	private Entidad entidad;
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "personas")
	private List<Organizacion> organizacions;

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

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	
	
}
