/**
 * 
 */
package com.common;

import com.BaseEntity;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class Area extends BaseEntity {

	private String nombre;
	private String activo;
	private Area parentArea;
	transient
	private List<Area> childAreas;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Area getParentArea() {
		return parentArea;
	}

	public void setParentArea(Area parentArea) {
		this.parentArea = parentArea;
	}

	public List<Area> getChildAreas() {
		return childAreas;
	}

	public void setChildAreas(List<Area> childAreas) {
		this.childAreas = childAreas;
	}

	@Override
	public String toString() {
		return "Area{" +
				"nombre='" + nombre + '\'' +
				", activo='" + activo + '\'' +
				", parentArea=" + parentArea +
				", childAreas=" + childAreas +
				'}';
	}
}
