/**
 * 
 */
package com.ecallac.rentcar.view;

import javax.validation.constraints.NotEmpty;

/**
 * @author efrain.calla
 *
 */
public class CategoriaView {
	private Long id;
	@NotEmpty
	private String descripsion;
	
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
	
}
