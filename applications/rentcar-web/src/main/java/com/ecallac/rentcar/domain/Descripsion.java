/**
 * 
 */
package com.ecallac.rentcar.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "descripcion")
public class Descripsion extends BaseEntity {
	private String descripsion;
	private String status;
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Dinero> getDineros() {
		return dineros;
	}
	public void setDineros(List<Dinero> dineros) {
		this.dineros = dineros;
	}
	
}
