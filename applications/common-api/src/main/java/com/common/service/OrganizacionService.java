/**
 * 
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface OrganizacionService extends BaseService<Organizacion>{
	Organizacion findOrganizacionByEntidadId(Long entidadId);
	List<Organizacion> findByRazonSocialONumeroIdentificacion(String termino);
	DataTablesOutput<Organizacion> findDataTablesList(DataTablesInput<Organizacion> dataTablesInput);
}
