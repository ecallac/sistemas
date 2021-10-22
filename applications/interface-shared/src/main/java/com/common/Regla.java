/**
 * 
 */
package com.common;

import java.util.List;

import com.BaseEntity;

/**
 * @author efrain
 *
 */
public class Regla extends BaseEntity {
	private String categoria;
	private String nombre;
	private String codigo;
	private String descripcion;
	private String tipoValor;
	private String activo;
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
	
	
}
