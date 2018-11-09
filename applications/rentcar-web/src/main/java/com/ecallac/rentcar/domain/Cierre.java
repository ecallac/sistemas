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
public class Cierre extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="vehiculo_id")
	private Vehiculo vehiculo;
	private Date fechainicio;
	private Date fechafin;
	private BigDecimal totalentrada;
	private BigDecimal totalsalida;
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
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
	public BigDecimal getTotalentrada() {
		return totalentrada;
	}
	public void setTotalentrada(BigDecimal totalentrada) {
		this.totalentrada = totalentrada;
	}
	public BigDecimal getTotalsalida() {
		return totalsalida;
	}
	public void setTotalsalida(BigDecimal totalsalida) {
		this.totalsalida = totalsalida;
	}
	
}
