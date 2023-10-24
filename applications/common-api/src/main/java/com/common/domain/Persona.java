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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "persona")
public class Persona extends BaseEntity {
	@Column(name = "tipo_documento_identificaion")
	private String tipoDocumentoIdentificaion;
	@Searchable
	private String numeroidentificacion;
	@Searchable
	private String nombres;
	@Searchable
	private String apellidos;
	@Column(name = "tipo_estado_civil")
	private String tipoEstadoCivil;
	@Searchable
	private String sexo;
	@Searchable
	private Date fechanacimiento;
	@Searchable
	private String email;
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	@Fetch(value = FetchMode.SELECT)
	private Entidad entidad;
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "personas")
	@Fetch(value = FetchMode.SELECT)
	private List<Organizacion> organizacions;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Organizacion> getOrganizacions() {
		return organizacions;
	}

	public void setOrganizacions(List<Organizacion> organizacions) {
		this.organizacions = organizacions;
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

	@Override
	public String toString() {
		return "Persona [tipoDocumentoIdentificaion=" + tipoDocumentoIdentificaion + ", numeroidentificacion="
				+ numeroidentificacion + ", nombres=" + nombres + ", apellidos=" + apellidos + ", tipoEstadoCivil="
				+ tipoEstadoCivil + ", sexo=" + sexo + ", fechanacimiento=" + fechanacimiento + ", email=" + email
				+ ", entidad=" + entidad + ", organizacions=" + organizacions + "]";
	}
	
	
}
