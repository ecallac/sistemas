/**
 * 
 */
package com.common.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "cargo")
public class Cargo extends BaseEntity {

	private String nombre;
	private String activo;
	private BigDecimal salariomin;
	private BigDecimal salariomax;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "cargo_parent_id")
	@Fetch(value = FetchMode.SELECT)
	private Cargo parentCargo;
	transient
	private List<Cargo> childCargos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Cargo getParentCargo() {
		return parentCargo;
	}

	public void setParentCargo(Cargo parentCargo) {
		this.parentCargo = parentCargo;
	}

	public List<Cargo> getChildCargos() {
		return childCargos;
	}

	public void setChildCargos(List<Cargo> childCargos) {
		this.childCargos = childCargos;
	}

	public BigDecimal getSalariomin() {
		return salariomin;
	}

	public void setSalariomin(BigDecimal salariomin) {
		this.salariomin = salariomin;
	}

	public BigDecimal getSalariomax() {
		return salariomax;
	}

	public void setSalariomax(BigDecimal salariomax) {
		this.salariomax = salariomax;
	}

	@Override
	public String toString() {
		return "Cargo{" +
				"nombre='" + nombre + '\'' +
				", activo='" + activo + '\'' +
				", salariomin=" + salariomin +
				", salariomax=" + salariomax +
				", parentCargo=" + parentCargo +
				", childCargos=" + childCargos +
				'}';
	}
}
