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

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "telefono")
public class Telefono extends BaseEntity {
	private String tipo;
	private String numero;
	private String codigoarea;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	@Fetch(value = FetchMode.SELECT)
	private Entidad entidad;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoarea() {
		return codigoarea;
	}

	public void setCodigoarea(String codigoarea) {
		this.codigoarea = codigoarea;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	@Override
	public String toString() {
		return "Telefono [tipo=" + tipo + ", numero=" + numero + ", codigoarea=" + codigoarea + ", entidad=" + entidad
				+ "]";
	}
	
	
}
