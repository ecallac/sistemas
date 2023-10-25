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
import java.math.BigDecimal;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sucursal")
public class Sucursal extends BaseEntity {
	@Searchable
	private String nombre;
	@Searchable
	private String estado;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "organizacion_id")
	@Fetch(value = FetchMode.SELECT)
	private Organizacion organizacion;
	@Column(name = "tipo_sucursal")
	private String tiposucursal;
	@Searchable
	private String nombrecorto;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "direccion_id")
	@Fetch(value = FetchMode.SELECT)
	private Direccion direccion;
}
