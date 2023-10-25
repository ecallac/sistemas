/**
 * 
 */
package com.common;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto extends BaseEntity {
	private Categoria categoria;
	private Marca marca;
	private Organizacion laboratorio;
	private String tipoproducto;
	private String tipopresentacion;
	private String tipounidadmedida;
	private String nombre;
	private String descripcion;
	private String uso;
	private String generico;
	private String puedevenderse;
	private String puedecomprarse;
	private String puedetransferirse;
	private String referenciainterna;
	private String codigobarras;
	private Double peso;
	private String volumen;
	private Integer stockinicial;
	private Integer fraccioninicial;
}
