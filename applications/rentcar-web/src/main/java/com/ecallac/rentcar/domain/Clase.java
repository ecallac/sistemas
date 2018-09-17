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
public class Clase extends BaseEntity {
	private String descripsion;
	private String status;
	transient
	private List<Vehiculo> vehiculos;
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
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
}
