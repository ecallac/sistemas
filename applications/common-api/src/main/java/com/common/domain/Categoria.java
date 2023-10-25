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
@Table(name = "categoria")
public class Categoria extends BaseEntity {
	@Searchable
	private String nombre;
	@Column(name = "tipo_categoria")
	private String tipocategoria;
	@Column(name = "tipo_estrategia_retiro")
	private String tipoestrategiaretiro;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "categoria_padre_id")
	@Fetch(value = FetchMode.SELECT)
	private Categoria categoriapadre;
	@Searchable
	private String status;
}
