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
public class ModeloView {
	private Long id;
	@NotEmpty
	private String marcaId;
	@NotEmpty
	private String descripsion;
	private MarcaView marca;

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

	public MarcaView getMarca() {
		if (this.marca==null && !StringUtils.isEmpty(marcaId)) {
			MarcaView view = new MarcaView();
			view.setId(Long.valueOf(this.marcaId));
			this.marca = view;
		}
		return marca;
	}

	public void setMarca(MarcaView marca) {
		this.marca = marca;
	}

	public String getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(String marcaId) {
		MarcaView marcaView = new MarcaView();
		marcaView.setId(Long.valueOf(marcaId));
		this.marca = marcaView;
		this.marcaId = marcaId;
	}
	
}
