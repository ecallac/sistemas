/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Vehiculo;

/**
 * @author efrain.calla
 *
 */
public interface VehiculoService {
	List<Vehiculo> findList();
	Vehiculo findById(Long id);
	void save(Vehiculo entity);
	void delete(Long id,String deletedBy);
}
