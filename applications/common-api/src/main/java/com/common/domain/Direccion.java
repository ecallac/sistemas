/**
 * 
 */
package com.common.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "direccion")
public class Direccion extends BaseEntity {
	private String direccionexacta;
	private String codigopostal;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	@Fetch(value = FetchMode.SELECT)
	private Entidad entidad;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ubigeo_id")
	@Fetch(value = FetchMode.SELECT)
	private Ubigeo ubigeo;
	private String esprincipal;
	private String estado;
	private String referencia;
	public String getDireccionexacta() {
		return direccionexacta;
	}
	public void setDireccionexacta(String direccionexacta) {
		this.direccionexacta = direccionexacta;
	}
	public String getCodigopostal() {
		return codigopostal;
	}
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	public Entidad getEntidad() {
		return entidad;
	}
	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}
	public Ubigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getEsprincipal() {
		return esprincipal;
	}
	public void setEsprincipal(String esprincipal) {
		this.esprincipal = esprincipal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public String toString() {
		return "Direccion{" +
				"direccionexacta='" + direccionexacta + '\'' +
				", codigopostal='" + codigopostal + '\'' +
				", entidad=" + entidad +
				", ubigeo=" + ubigeo +
				", esprincipal='" + esprincipal + '\'' +
				", estado='" + estado + '\'' +
				", referencia='" + referencia + '\'' +
				'}';
	}
}
