/**
 * 
 */
package com.common.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ubigeo")
public class Ubigeo{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name = "codigoubigeo")
	private String codigoUbigeo;
	private String descripcion;
	private String abreviatura;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "parent_ubigeo_id")
	@Fetch(value = FetchMode.SELECT)
	private Ubigeo parentUbigeo;
	private String estado;
	@Column(name = "tipo_ubigeo")
	private String tipoubigeo;
	transient
	private List<Ubigeo> childubigeos;
	transient
	private List<CentroPoblado> centroPoblados;
	transient
	private List<Direccion> direccions;
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoubigeo() {
		return tipoubigeo;
	}
	public void setTipoubigeo(String tipoubigeo) {
		this.tipoubigeo = tipoubigeo;
	}
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
