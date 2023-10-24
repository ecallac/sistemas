/**
 * 
 */
package com.common.service;

import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

/**
 * @author efrain.calla
 *
 */
public interface EntidadService extends BaseService<Entidad>{
	void savePersona(Persona entidad);
	void saveOrganizacion(Organizacion entidad);
}
