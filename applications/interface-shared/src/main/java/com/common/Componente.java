/**
 * 
 */
package com.common;

import com.BaseEntity;

/**
 * @author efrain
 *
 */
public class Componente extends BaseEntity {
	private String nombre;
	private String descripcion;
	private String status;
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
