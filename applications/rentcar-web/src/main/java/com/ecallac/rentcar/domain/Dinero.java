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
public class Dinero extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="descripsion_id")
	private Descripsion descripsion;
	@ManyToOne
	@JoinColumn(name="tipo_id")
	private Tipo tipo;
	@ManyToOne
	@JoinColumn(name="vehiculo_id")
	private Vehiculo vehiculo;
	private Date fecha;
	private BigDecimal valor;
	public Descripsion getDescripsion() {
		return descripsion;
	}
	public void setDescripsion(Descripsion descripsion) {
		this.descripsion = descripsion;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
