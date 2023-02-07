/**
 * 
 */
package com.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "marca")
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
