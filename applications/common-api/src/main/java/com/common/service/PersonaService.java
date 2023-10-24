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
public interface PersonaService extends BaseService<Persona>{
	Persona findPersonaByEntidadId(Long entidadId);
	List<Persona> findByNombreOApellidoONumeroDocumento(String termino);
	DataTablesOutput<Persona> findDataTablesList(DataTablesInput<Persona> dataTablesInput);
}
