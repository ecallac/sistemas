/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain
 *
 */
public class ComponenteView extends BaseEntity {
	@NotEmpty
	private String nombre;
	private String descripcion;
	@NotEmpty
	private String status;
	@NotEmpty
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
}
