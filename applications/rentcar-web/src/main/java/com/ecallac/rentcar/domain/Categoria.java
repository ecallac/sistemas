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
public class Categoria extends BaseEntity {
	private String descripsion;
	private String status;
	transient
	private List<Descripsion> descripsions;
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
	public List<Descripsion> getDescripsions() {
		return descripsions;
	}
	public void setDescripsions(List<Descripsion> descripsions) {
		this.descripsions = descripsions;
	}
	
	
}
