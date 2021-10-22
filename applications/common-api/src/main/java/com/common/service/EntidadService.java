/**
 * 
 */
package com.common.service;

import java.util.List;

import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

/**
 * @author efrain.calla
 *
 */
public interface EntidadService extends BaseService<Entidad>{
	Persona findPersonaByEntidadId(Long entidadId);
	Organizacion findOrganizacionByEntidadId(Long entidadId);
	List<Persona> findByNombreOApellidoONumeroDocumento(String termino);
	void savePersona(Persona entidad);
}
