/**
 * 
 */
package com.common.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "regla")
public class Regla extends BaseEntity {
	private String categoria;
	private String nombre;
	private String codigo;
	private String descripcion;
	@Column(name = "tipo_valor")
	private String tipoValor;
	private String activo;
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "regla", fetch = FetchType.EAGER)
//	@Fetch(value = FetchMode.SELECT)
	transient
	private List<ReglaDetalle> reglaDetalles;
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipoValor() {
		return tipoValor;
	}
	public void setTipoValor(String tipoValor) {
		this.tipoValor = tipoValor;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public List<ReglaDetalle> getReglaDetalles() {
		return reglaDetalles;
	}
	public void setReglaDetalles(List<ReglaDetalle> reglaDetalles) {
		this.reglaDetalles = reglaDetalles;
	}
	@Override
	public String toString() {
		return "Regla [categoria=" + categoria + ", nombre=" + nombre + ", codigo=" + codigo + ", descripcion="
				+ descripcion + ", tipoValor=" + tipoValor + ", activo=" + activo + ", reglaDetalles=" + reglaDetalles
				+ "]";
	}
	
	
}
