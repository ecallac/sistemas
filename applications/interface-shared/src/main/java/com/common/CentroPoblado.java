/**
 * 
 */
package com.common;

import java.util.List;

import com.BaseEntity;

/**
 * @author efrain.calla
 *
 */
public class CentroPoblado extends BaseEntity {
	private String status;
	private String descripcion;
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
