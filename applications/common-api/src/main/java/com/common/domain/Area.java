/**
 * 
 */
package com.common.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Entity
@Table(name = "area")
public class Area extends BaseEntity {
	@Searchable
	private String nombre;
	private String activo;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "area_padre_id")
	@Fetch(value = FetchMode.SELECT)
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
