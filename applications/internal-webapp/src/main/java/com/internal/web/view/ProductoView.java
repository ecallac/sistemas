/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoView extends BaseEntity {
	@NotEmpty
	private String nombre;
	private CategoriaView categoria;
	@NotEmpty
	private String categoriaId;
	private MarcaView marca;
	@NotEmpty
	private String marcaId;
	private OrganizacionView laboratorio;
	private String laboratorioId;
	private String tipoproducto;
	private String tipopresentacion;
	private String tipounidadmedida;
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
