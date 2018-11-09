/**
 * 
 */
package com.ecallac.rentcar.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Alquiler extends BaseEntity {
	private String status;
	private BigDecimal cuentapactada;
	private Date fechainicio;
	private Date fechafin;
	@ManyToOne
	@JoinColumn(name="vehiculo_id")
	private Vehiculo vehiculo;
	@ManyToOne
	@JoinColumn(name="conductor_id")
	private Conductor conductor;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getCuentapactada() {
		return cuentapactada;
	}
	public void setCuentapactada(BigDecimal cuentapactada) {
		this.cuentapactada = cuentapactada;
	}
	public Date getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	public Date getFechafin() {
		return fechafin;
	}
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Conductor getConductor() {
		return conductor;
	}
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	
	
}
