/**
 * 
 */
package com.ecallac.rentcar.view;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

/**
 * @author efrain
 *
 */
public class AlquilerView {
	private Long id;
	@NotEmpty
	private String vehiculoId;
	@NotEmpty
	private String conductorId;
	private VehiculoView vehiculo;
	private ConductorView conductor;
	@NotEmpty
	private String status;
	@NotEmpty
	private String cuentapactada;
	@NotEmpty
	private String fechainicio;
	@NotEmpty
	private String fechafin;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCuentapactada() {
		return cuentapactada;
	}
	public void setCuentapactada(String cuentapactada) {
		this.cuentapactada = cuentapactada;
	}
	public String getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getFechafin() {
		return fechafin;
	}
	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}
	public String getVehiculoId() {
		if (vehiculoId==null && vehiculo!=null && vehiculo.getId()!=null) {
			return vehiculo.getId().toString();
		}
		return vehiculoId;
	}
	public void setVehiculoId(String vehiculoId) {
		VehiculoView view = new VehiculoView();
		if (!StringUtils.isEmpty(vehiculoId)) {
			view.setId(Long.valueOf(vehiculoId));
		}
		this.vehiculo = view;
		this.vehiculoId = vehiculoId;
	}
	public String getConductorId() {
		if (conductorId==null && conductor!=null && conductor.getId()!=null) {
			return conductor.getId().toString();
		}
		return conductorId;
	}
	public void setConductorId(String conductorId) {
		ConductorView view = new ConductorView();
		if (!StringUtils.isEmpty(conductorId)) {
			view.setId(Long.valueOf(conductorId));
		}
		this.conductor = view;
		this.conductorId = conductorId;
	}
	public VehiculoView getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(VehiculoView vehiculo) {
		this.vehiculo = vehiculo;
	}
	public ConductorView getConductor() {
		return conductor;
	}
	public void setConductor(ConductorView conductor) {
		this.conductor = conductor;
	}
	
}
