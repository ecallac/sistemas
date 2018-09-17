/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Clase;

/**
 * @author efrain.calla
 *
 */
public interface ClaseService {
	List<Clase> findList();
	Clase findById(Long id);
	void save(Clase entity);
	void delete(Long id,String deletedBy);
}
