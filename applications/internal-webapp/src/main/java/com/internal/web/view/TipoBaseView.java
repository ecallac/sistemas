/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author efrain
 *
 */
public class TipoBaseView extends BaseEntity {
	@NotEmpty
	private String categoria;
	@NotEmpty
	private String codigo;
	@NotEmpty
	private String descripcion;
	@NotEmpty
	private String activo;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "TipoBaseView{" +
				"categoria='" + categoria + '\'' +
				", codigo='" + codigo + '\'' +
				", descripcion='" + descripcion + '\'' +
				", activo='" + activo + '\'' +
				'}';
	}
}
