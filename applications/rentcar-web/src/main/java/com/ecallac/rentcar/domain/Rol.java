/**
 * 
 */
package com.ecallac.rentcar.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author efrain.calla
 *
 */
@Entity
public class Rol extends BaseEntity{
	
	private String status; 
	private String descripcion;
	
//	@OneToMany(mappedBy="rol",fetch=FetchType.EAGER)
//	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	transient
	private List<Usuario> usuarios;
	
	public String getNameWithPrefix(){
		return "ROLE_"+descripcion;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
