/**
 * 
 */
package com.ecallac.rentcar.view;

import java.util.Date;
import java.util.List;


/**
 * @author efrain
 *
 */
public class OperacionView {
	private AlquilerView alquiler;
	private VehiculoView vehiculo;
	private Date fecha;
	private Integer nvueltas;
	private String alquilerId;
	private String vahiculoId;
	private List<DineroView> dineros;
	public AlquilerView getAlquiler() {
		return alquiler;
	}
	public void setAlquiler(AlquilerView alquiler) {
		this.alquiler = alquiler;
	}
	public VehiculoView getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoView vehiculo) {
		this.vehiculo = vehiculo;
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
	public String getAlquilerId() {
		return alquilerId;
	}
	public void setAlquilerId(String alquilerId) {
		this.alquilerId = alquilerId;
	}
	public String getVahiculoId() {
		return vahiculoId;
	}
	public void setVahiculoId(String vahiculoId) {
		this.vahiculoId = vahiculoId;
	}
	public List<DineroView> getDineros() {
		return dineros;
	}
	public void setDineros(List<DineroView> dineros) {
		this.dineros = dineros;
	}
	
}
