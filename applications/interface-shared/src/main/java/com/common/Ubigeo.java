/**
 * 
 */
package com.common;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class Ubigeo{
	private Long id;
	private String codigoUbigeo;
	private String descripcion;
	private String abreviatura;
	private Ubigeo parentUbigeo;
	transient
	private List<Ubigeo> childubigeos;
	transient
	private List<CentroPoblado> centroPoblados;
	transient
	private List<Direccion> direccions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}
	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public Ubigeo getParentUbigeo() {
		return parentUbigeo;
	}
	public void setParentUbigeo(Ubigeo parentUbigeo) {
		this.parentUbigeo = parentUbigeo;
	}
	public List<Ubigeo> getChildubigeos() {
		return childubigeos;
	}
	public void setChildubigeos(List<Ubigeo> childubigeos) {
		this.childubigeos = childubigeos;
	}
	public List<CentroPoblado> getCentroPoblados() {
		return centroPoblados;
	}
	public void setCentroPoblados(List<CentroPoblado> centroPoblados) {
		this.centroPoblados = centroPoblados;
	}
	public List<Direccion> getDireccions() {
		return direccions;
	}
	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}
	@Override
	public String toString() {
		return "Ubigeo [id=" + id + ", codigoUbigeo=" + codigoUbigeo + ", descripcion=" + descripcion + ", abreviatura="
				+ abreviatura + ", parentUbigeo=" + parentUbigeo + ", childubigeos=" + childubigeos
				+ ", centroPoblados=" + centroPoblados + ", direccions=" + direccions + "]";
	}
	
}
