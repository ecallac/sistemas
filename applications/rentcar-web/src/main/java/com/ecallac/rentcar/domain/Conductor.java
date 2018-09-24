/**
 * 
 */
package com.ecallac.rentcar.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Conductor extends BaseEntity {
	private String status;
	@Column(name = "numerodocumento")
	private String numeroDocumento;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
