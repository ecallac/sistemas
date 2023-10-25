/**
 * 
 */
package com.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "empleado")
public class Empleado extends BaseEntity {
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "persona_id")
	@Fetch(value = FetchMode.SELECT)
	private Persona persona;
	@Searchable
	private String codigo;
	private Date fechaingreso;
	@Column(name = "tipo_titulo")
	private String tipotitulo;
	@Searchable
	private String apreciacion;
	@Searchable
	private String cargofamiliar;
	@Searchable
	private String estado;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "reporta_id")
	@Fetch(value = FetchMode.SELECT)
	private Empleado reporta;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "supervisorausencias_id")
	@Fetch(value = FetchMode.SELECT)
	private Empleado supervisorausencias;
}
