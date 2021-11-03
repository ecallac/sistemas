/**
 * 
 */
package com.common.domain;

import java.util.List;

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
@Table(name = "centropoblado")
public class CentroPoblado extends BaseEntity {
	private String status;
	private String descripcion;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ubigeo_id")
	@Fetch(value = FetchMode.SELECT)
	private Ubigeo ubigeo;
	transient
	private List<Direccion> direccions;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Ubigeo getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(Ubigeo ubigeo) {
		this.ubigeo = ubigeo;
	}
	public List<Direccion> getDireccions() {
		return direccions;
	}
	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}
	@Override
	public String toString() {
		return "CentroPoblado [status=" + status + ", descripcion=" + descripcion + ", ubigeo=" + ubigeo
				+ ", direccions=" + direccions + "]";
	}
	
}
