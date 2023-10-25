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

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "areasucursal")
public class AreaSucursal extends BaseEntity {
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "sucursal_id")
	@Fetch(value = FetchMode.SELECT)
	private Sucursal sucursal;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "area_id")
	@Fetch(value = FetchMode.SELECT)
	private Area area;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "responsable_id")
	@Fetch(value = FetchMode.SELECT)
	private Empleado responsable;
	@Searchable
	private String estado;
}
