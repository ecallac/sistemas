/**
 * 
 */
package com.ecallac.rentcar.view;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author efrain
 *
 */
public class DineroView {
	private DescripsionView descripsion;
	private TipoView tipo;
	private VehiculoView vehiculo;
	private Date fecha;
	private BigDecimal valor;
	private String descripsionId;
	private String tipoId;
	private String vehiculoId;
	public DescripsionView getDescripsion() {
		return descripsion;
	}
	public void setDescripsion(DescripsionView descripsion) {
		this.descripsion = descripsion;
	}
	public TipoView getTipo() {
		return tipo;
	}
	public void setTipo(TipoView tipo) {
		this.tipo = tipo;
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getDescripsionId() {
		return descripsionId;
	}
	public void setDescripsionId(String descripsionId) {
		this.descripsionId = descripsionId;
	}
	public String getTipoId() {
		return tipoId;
	}
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	public String getVehiculoId() {
		return vehiculoId;
	}
	public void setVehiculoId(String vehiculoId) {
		this.vehiculoId = vehiculoId;
	}
	
}
