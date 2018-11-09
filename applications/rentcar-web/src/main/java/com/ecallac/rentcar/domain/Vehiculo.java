/**
 * 
 */
package com.ecallac.rentcar.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Vehiculo extends BaseEntity {
	private String status;
	@ManyToOne
	@JoinColumn(name="modelo_id")
	private Modelo modelo;
	@ManyToOne
	@JoinColumn(name="clase_id")
	private Clase clase;
	private String matricula;
	private String nasientos;
	private String transmision;
	private BigDecimal cuentaxdia;
	private Integer cilindrada;
	private Integer anio;
	private String combustible;
	private String color;
	private String descripsion;
	@Lob
    private byte[] foto;
	
	transient
	private List<Alquiler> alquilers;
	transient
	private List<Trabajo> trabajos;
	transient
	private List<Dinero> dineros;
	transient
	private List<Cierre> cierres;
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Alquiler> getAlquilers() {
		return alquilers;
	}

	public void setAlquilers(List<Alquiler> alquilers) {
		this.alquilers = alquilers;
	}

	public List<Trabajo> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}

	public List<Dinero> getDineros() {
		return dineros;
	}

	public void setDineros(List<Dinero> dineros) {
		this.dineros = dineros;
	}

	public List<Cierre> getCierres() {
		return cierres;
	}

	public void setCierres(List<Cierre> cierres) {
		this.cierres = cierres;
	}
	
	
}
