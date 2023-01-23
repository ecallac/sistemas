/**
 * 
 */
package com.common;

import com.BaseEntity;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class Ubigeo extends BaseEntity {
	private String codigo;
	private String descripcion;
	private String abreviatura;
	private Ubigeo parentUbigeo;
	private String estado;
	private String tipoubigeo;
	private List<Ubigeo> childubigeos;
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
	public List<Direccion> getDireccions() {
		return direccions;
	}
	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Ubigeo{" +
				"codigo='" + codigo + '\'' +
				", descripcion='" + descripcion + '\'' +
				", abreviatura='" + abreviatura + '\'' +
				", parentUbigeo=" + parentUbigeo +
				", estado='" + estado + '\'' +
				", tipoubigeo='" + tipoubigeo + '\'' +
				", childubigeos=" + childubigeos +
				", direccions=" + direccions +
				'}';
	}
}
