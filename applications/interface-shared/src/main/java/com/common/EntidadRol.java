/**
 * 
 */
package com.common;

import com.BaseEntity;

/**
 * @author efrain
 *
 */
public class EntidadRol extends BaseEntity {
	private String estado;
	private String tipoEntidadrol;
	private Entidad entidad;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoEntidadrol() {
		return tipoEntidadrol;
	}

	public void setTipoEntidadrol(String tipoEntidadrol) {
		this.tipoEntidadrol = tipoEntidadrol;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	
	
}
