/**
 * 
 */
package com.common.services;

import java.util.List;

import com.common.domain.Telefono;

/**
 * @author efrain.calla
 *
 */
public interface TelefonoService {
	void save(Telefono telefono);
	Telefono findById(Long id);
	List<Telefono> findAll();
	List<Telefono> findByEntidadId(Long id);
}
