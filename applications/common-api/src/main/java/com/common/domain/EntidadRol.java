/**
 * 
 */
package com.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "entidadrol")
public class EntidadRol extends BaseEntity {
	private String estado;
	@Column(name = "tipo_entidadrol")
	private String tipoEntidadrol;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	@Fetch(value = FetchMode.SELECT)
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

	@Override
	public String toString() {
		return "EntidadRol [estado=" + estado + ", tipoEntidadrol=" + tipoEntidadrol + ", entidad=" + entidad + "]";
	}
	
	
}
