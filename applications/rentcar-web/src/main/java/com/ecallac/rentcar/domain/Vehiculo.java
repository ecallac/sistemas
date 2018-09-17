/**
 * 
 */
package com.ecallac.rentcar.domain;

import javax.persistence.Entity;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Vehiculo extends BaseEntity {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
