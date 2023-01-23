/**
 * 
 */
package com.common;

import com.BaseEntity;

/**
 * @author efrain.calla
 *
 */
public class Direccion extends BaseEntity {
	private String direccionexacta;
	private String codigopostal;
	private Entidad entidad;
	private Ubigeo ubigeo;
	private String esprincipal;
	private String estado;
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
	@Override
	public String toString() {
		return "Direccion [direccionexacta=" + direccionexacta + ", codigopostal=" + codigopostal + ", entidad="
				+ entidad + ", ubigeo=" + ubigeo + ", esprincipal=" + esprincipal
				+ ", estado=" + estado + "]";
	}
	
}
