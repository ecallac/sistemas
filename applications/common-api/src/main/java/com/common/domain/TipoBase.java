/**
 * 
 */
package com.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 19:28:46
 */
@Entity
@Table(name = "tipobase")
public class TipoBase extends BaseEntity {
	@Column(name = "categoria",nullable = false)
	private String categoria;
	
	@Column(name = "codigo",nullable = false)
	private String codigo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "activo",nullable = false)
	private String activo;

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the activo
	 */
	public String getActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(String activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "TipoBase [categoria=" + categoria + ", codigo=" + codigo + ", descripcion=" + descripcion + ", activo="
				+ activo + "]";
	}
	
	
}
