/**
 * 
 */
package com.common.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "regladetalle")
public class ReglaDetalle extends BaseEntity {
	@Searchable
	private String condicion;
	@Searchable
	private Double valornumero;
	@Searchable
	private String valorcadena;
	@Searchable
	private Date valorfecha;
	@Searchable
	private String activo;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "regla_id")
	@Fetch(value = FetchMode.SELECT)
	private Regla regla;

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Double getValornumero() {
		return valornumero;
	}

	public void setValornumero(Double valornumero) {
		this.valornumero = valornumero;
	}

	public String getValorcadena() {
		return valorcadena;
	}

	public void setValorcadena(String valorcadena) {
		this.valorcadena = valorcadena;
	}

	public Date getValorfecha() {
		return valorfecha;
	}

	public void setValorfecha(Date valorfecha) {
		this.valorfecha = valorfecha;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Regla getRegla() {
		return regla;
	}

	public void setRegla(Regla regla) {
		this.regla = regla;
	}

	@Override
	public String toString() {
		return "ReglaDetalle{" +
				"condicion='" + condicion + '\'' +
				", valornumero=" + valornumero +
				", valorcadena='" + valorcadena + '\'' +
				", valorfecha='" + valorfecha + '\'' +
				", activo='" + activo + '\'' +
				", regla=" + regla +
				'}';
	}
}
