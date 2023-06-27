/**
 * 
 */
package com.internal.web.view;

import com.BaseEntity;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
public class CargoView extends BaseEntity {
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String activo;
	@NotEmpty
	private BigDecimal salariomin;
	@NotEmpty
	private BigDecimal salariomax;
	private CargoView parentCargo;
	@NotEmpty
	private String parentCargoId;
	transient
	private List<CargoView> childCargos;

	public String getParentCargoId() {
		return parentCargoId;
	}

	public void setParentCargoId(String parentCargoId) {
		this.parentCargoId = parentCargoId;
		CargoView view = new CargoView();
		view.setId(Long.valueOf(parentCargoId));
		this.parentCargo = view;
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

	public CargoView getParentCargo() {
		if (this.parentCargo==null && StringUtils.isNotBlank(parentCargoId)) {
			CargoView view = new CargoView();
			view.setId(Long.valueOf(this.parentCargoId));
			this.parentCargo = view;
		}
		return parentCargo;
	}

	public void setParentCargo(CargoView parentCargo) {
		this.parentCargo = parentCargo;
	}

	public List<CargoView> getChildCargos() {
		return childCargos;
	}

	public void setChildCargos(List<CargoView> childCargos) {
		this.childCargos = childCargos;
	}

	public BigDecimal getSalariomin() {
		return salariomin;
	}

	public void setSalariomin(BigDecimal salariomin) {
		this.salariomin = salariomin;
	}

	public BigDecimal getSalariomax() {
		return salariomax;
	}

	public void setSalariomax(BigDecimal salariomax) {
		this.salariomax = salariomax;
	}

	@Override
	public String toString() {
		return "CargoView{" +
				"nombre='" + nombre + '\'' +
				", activo='" + activo + '\'' +
				", salariomin=" + salariomin +
				", salariomax=" + salariomax +
				", parentCargo=" + parentCargo +
				", parentCargoId='" + parentCargoId + '\'' +
				", childCargos=" + childCargos +
				'}';
	}
}
