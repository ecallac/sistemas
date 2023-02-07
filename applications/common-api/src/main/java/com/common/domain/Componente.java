/**
 * 
 */
package com.common.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "componente")
public class Componente extends BaseEntity {
	@Searchable
	private String nombre;
	@Searchable
	private String descripcion;
	private String status;
	@Column(name = "tipo_componente")
	private String tipoComponnte;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoComponnte() {
		return tipoComponnte;
	}

	public void setTipoComponnte(String tipoComponnte) {
		this.tipoComponnte = tipoComponnte;
	}

	@Override
	public String toString() {
		return "Componente{" +
				"nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				", status='" + status + '\'' +
				", tipoComponnte='" + tipoComponnte + '\'' +
				'}';
	}
}
