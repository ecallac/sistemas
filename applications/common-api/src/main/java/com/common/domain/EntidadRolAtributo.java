/**
 * 
 */
package com.common.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * @author efrain
 *
 */
@Entity
@Table(name = "entidadrolatributo")
public class EntidadRolAtributo extends BaseEntity {
	private String status;
	@Column(name = "tipo_atributo")
	private String tipoAtributo;
	private String valor;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "entidadrol_id")
	@Fetch(value = FetchMode.SELECT)
	private EntidadRol entidadRol;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoAtributo() {
		return tipoAtributo;
	}

	public void setTipoAtributo(String tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public EntidadRol getEntidadRol() {
		return entidadRol;
	}

	public void setEntidadRol(EntidadRol entidadRol) {
		this.entidadRol = entidadRol;
	}

	@Override
	public String toString() {
		return "EntidadRolAtributo{" +
				"status='" + status + '\'' +
				", tipoAtributo='" + tipoAtributo + '\'' +
				", valor='" + valor + '\'' +
				", entidadRol=" + entidadRol +
				'}';
	}
}
