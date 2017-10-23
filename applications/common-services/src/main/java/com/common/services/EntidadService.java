/**
 * 
 */
package com.common.services;

import java.util.List;

import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

/**
 * @author efrain.calla
 *
 */
public interface EntidadService {
	void save(Organizacion organizacion);
	void save(Persona persona);
	Entidad findById(Long id);
	List<Organizacion> findAllOrganizacion();
	List<Persona> findAllPersona();
}
