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
public class Marca extends BaseEntity {
	private String descripsion;
	private String status;
	
	transient
	private List<Modelo> modelos;
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
	public List<Modelo> getModelos() {
		return modelos;
	}
	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}
	
}
