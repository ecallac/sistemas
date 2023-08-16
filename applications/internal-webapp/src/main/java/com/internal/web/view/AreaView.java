/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class AreaView extends BaseEntity {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String activo;
	private String activoDescripcion;
	private String activoType;
	@Getter
	private String ids[];
	private AreaView parentArea;
	@NotEmpty
	private String parentAreaId;
	transient
	private List<AreaView> childAreas;

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
		AreaView view = new  AreaView();
		view.setId(Long.valueOf(parentAreaId));
		this.parentArea = view;
	}

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

	public AreaView getParentArea() {
		if (this.parentArea==null && StringUtils.isNotBlank(parentAreaId)) {
			AreaView view = new  AreaView();
			view.setId(Long.valueOf(this.parentAreaId));
			this.parentArea = view;
		}
		return parentArea;
	}

	public void setParentArea(AreaView parentArea) {
		this.parentArea = parentArea;
	}

	public List<AreaView> getChildAreas() {
		return childAreas;
	}

	public void setChildAreas(List<AreaView> childAreas) {
		this.childAreas = childAreas;
	}

	public String getActivoDescripcion() {
		return activoDescripcion;
	}

	public void setActivoDescripcion(String activoDescripcion) {
		this.activoDescripcion = activoDescripcion;
	}

	public String getActivoType() {
		return activoType;
	}

	public void setActivoType(String activoType) {
		this.activoType = activoType;
	}

	@Override
	public String toString() {
		return "AreaView{" +
				"nombre='" + nombre + '\'' +
				", activo='" + activo + '\'' +
				", activoDescripcion='" + activoDescripcion + '\'' +
				", activoType='" + activoType + '\'' +
				", parentArea=" + parentArea +
				", parentAreaId='" + parentAreaId + '\'' +
				", childAreas=" + childAreas +
				'}';
	}
}
