/**
 * 
 */
package com.ecallac.rentcar.domain;

import java.util.List;

import javax.persistence.Entity;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Tipo extends BaseEntity {
	private String descripsion;
	private String status;
	private String categoria;
	private String codigo;
	
	transient
	private List<Dinero> dineros;
	public String getDescripsion() {
		return descripsion;
	}
	public void setDescripsion(String descripsion) {
		this.descripsion = descripsion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public List<Dinero> getDineros() {
		return dineros;
	}
	public void setDineros(List<Dinero> dineros) {
		this.dineros = dineros;
	}
	
	
}
