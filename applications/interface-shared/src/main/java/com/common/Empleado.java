/**
 * 
 */
package com.common;

import com.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author efrain
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Empleado extends BaseEntity {
	private Persona persona;
	private String codigo;
	private Date fechaingreso;
	private String tipotitulo;
	private String apreciacion;
	private String cargofamiliar;
	private String estado;
	private Empleado reporta;
	private Empleado supervisorausencias;
}
