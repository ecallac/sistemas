/**
 * 
 */
package com.ecallac.rentcar.view;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

/**
 * @author efrain.calla
 *
 */
public class DescripsionView {
	private Long id;
	@NotEmpty
	private String descripsion;
	@NotEmpty
	private String categoriaId;
	private CategoriaView categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripsion() {
		return descripsion;
	}

	public void setDescripsion(String descripsion) {
		this.descripsion = descripsion;
	}
	

	public CategoriaView getCategoria() {
		if (this.categoria==null && !StringUtils.isEmpty(categoriaId)) {
			CategoriaView view = new CategoriaView();
			view.setId(Long.valueOf(this.categoriaId));
			this.categoria = view;
		}
		return categoria;
	}

	public void setCategoria(CategoriaView categoria) {
		this.categoria = categoria;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		CategoriaView categoriaView = new CategoriaView();
		categoriaView.setId(Long.valueOf(categoriaId));
		this.categoria = categoriaView;
		this.categoriaId = categoriaId;
	}
	
}
