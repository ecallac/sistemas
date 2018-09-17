/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Conductor;

/**
 * @author efrain.calla
 *
 */
public interface ConductorService {
	List<Conductor> findList();
	Conductor findById(Long id);
	void save(Conductor entity);
	void delete(Long id,String deletedBy);
}
