/**
 * 
 */
package com.ecallac.rentcar.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Trabajo extends BaseEntity {
	@ManyToOne
	@JoinColumn(name="alquiler_id")
	private Alquiler alquiler;
	private Date fecha;
	private Integer nvueltas;
	
	public Alquiler getAlquiler() {
		return alquiler;
	}
	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getNvueltas() {
		return nvueltas;
	}
	public void setNvueltas(Integer nvueltas) {
		this.nvueltas = nvueltas;
	}
	
}
