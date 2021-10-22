/**
 * 
 */
package com.common;

import com.BaseEntity;

/**
 * @author efrain
 *
 */
public class ReglaDetalle extends BaseEntity {
	private String condicion;
	private Double valornumero;
	private String valorcadena;
	private String activo;
	private Regla regla;
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public Double getValornumero() {
		return valornumero;
	}
	public void setValornumero(Double valornumero) {
		this.valornumero = valornumero;
	}
	public String getValorcadena() {
		return valorcadena;
	}
	public void setValorcadena(String valorcadena) {
		this.valorcadena = valorcadena;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public Regla getRegla() {
		return regla;
	}
	public void setRegla(Regla regla) {
		this.regla = regla;
	}
	
	
}
