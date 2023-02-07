/**
 * 
 */
package com.internal.web.beans;

import com.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author efrain
 *
 */
public class MarcaView extends BaseEntity {
	@NotEmpty
	private String descripcion;
	@NotEmpty
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
		return "MarcaView{" +
				"descripcion='" + descripcion + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
