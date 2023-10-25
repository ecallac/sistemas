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
@Table(name = "producto")
public class Producto extends BaseEntity {
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	@Fetch(value = FetchMode.SELECT)
	private Categoria categoria;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "marca_id")
	@Fetch(value = FetchMode.SELECT)
	private Marca marca;
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "laboratorio_id")
	@Fetch(value = FetchMode.SELECT)
	private Organizacion laboratorio;
	@Column(name = "tipo_producto")
	private String tipoproducto;
	@Column(name = "tipo_presentacion")
	private String tipopresentacion;
	@Column(name = "tipo_unidadmedida")
	private String tipounidadmedida;
	@Searchable
	private String nombre;
	@Searchable
	private String descripcion;
	@Searchable
	private String uso;
	@Searchable
	private String generico;
	private String puedevenderse;
	private String puedecomprarse;
	private String puedetransferirse;
	private String referenciainterna;
	@Searchable
	private String codigobarras;
	private Double peso;
	@Searchable
	private String volumen;
	private Integer stockinicial;
	private Integer fraccioninicial;
}
