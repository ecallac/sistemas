/**
 * 
 */
package com.ecallac.rentcar.view;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

/**
 * @author efrain.calla
 *
 */
public class VehiculoView {
	private Long id;
	private ModeloView modelo;
	private ClaseView clase;
	@NotEmpty
	private String modeloId;
	@NotEmpty
	private String claseId;
	@NotEmpty
	private String matricula;
	private String nasientos;
	private String transmision;
	private BigDecimal cuentaxdia;
	private Integer cilindrada;
	private Integer anio;
	private String combustible;
	private String color;
	private String descripsion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ModeloView getModelo() {
		if (this.modelo==null && !StringUtils.isEmpty(modeloId)) {
			ModeloView view = new ModeloView();
			view.setId(Long.valueOf(this.modeloId));
			this.modelo = view;
		}
		return modelo;
	}

	public void setModelo(ModeloView modelo) {
		this.modelo = modelo;
	}

	public ClaseView getClase() {
		if (this.clase==null && !StringUtils.isEmpty(claseId)) {
			ClaseView view = new ClaseView();
			view.setId(Long.valueOf(this.claseId));
			this.clase = view;
		}
		return clase;
	}

	public void setClase(ClaseView clase) {
		this.clase = clase;
	}

	public String getModeloId() {
		return modeloId;
	}

	public void setModeloId(String modeloId) {
		ModeloView view = new ModeloView();
		if (!StringUtils.isEmpty(modeloId)) {
			view.setId(Long.valueOf(modeloId));
		}
		this.modelo = view;
		this.modeloId = modeloId;
	}

	public String getClaseId() {
		return claseId;
	}

	public void setClaseId(String claseId) {
		ClaseView view = new ClaseView();
		if (!StringUtils.isEmpty(claseId)) {
			view.setId(Long.valueOf(claseId));
		}
		this.clase = view;
		this.claseId = claseId;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNasientos() {
		return nasientos;
	}

	public void setNasientos(String nasientos) {
		this.nasientos = nasientos;
	}

	public String getTransmision() {
		return transmision;
	}

	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}


	public BigDecimal getCuentaxdia() {
		return cuentaxdia;
	}

	public void setCuentaxdia(BigDecimal cuentaxdia) {
		this.cuentaxdia = cuentaxdia;
	}

	public Integer getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Integer cilindrada) {
		this.cilindrada = cilindrada;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescripsion() {
		return descripsion;
	}

	public void setDescripsion(String descripsion) {
		this.descripsion = descripsion;
	}
	
	
}
