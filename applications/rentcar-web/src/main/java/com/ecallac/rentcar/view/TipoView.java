/**
 * 
 */
package com.ecallac.rentcar.view;

import javax.validation.constraints.NotEmpty;

/**
 * @author efrain
 *
 */
public class TipoView {
	private Long id;
	@NotEmpty
	private String descripsion;
	@NotEmpty
	private String categoria;
	@NotEmpty
	private String codigo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripsion() {
		return descripsion;
	}
	public void setDescripsion(String descripsion) {
		this.descripsion = descripsion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
