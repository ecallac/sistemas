/**
 * 
 */
package com.common;

import com.BaseEntity;

/**
 * @author efrain
 *
 */
public class Marca extends BaseEntity {
	private String descripcion;
	private String status;

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

	@Override
	public String toString() {
		return "Marca{" +
				"descripcion='" + descripcion + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
