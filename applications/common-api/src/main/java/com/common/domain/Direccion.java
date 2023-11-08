/**
 * 
 */
package com.common.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "direccion")
public class Direccion extends BaseEntity {
	@Searchable
	private String direccionexacta;
	@Searchable
	private String codigopostal;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "entidad_id")
	@Fetch(value = FetchMode.SELECT)
	private Entidad entidad;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ubigeo_id")
	@Fetch(value = FetchMode.SELECT)
	private Ubigeo ubigeo;
	private String esprincipal;
	@Searchable
	private String estado;
	@Searchable
	private String referencia;
}
