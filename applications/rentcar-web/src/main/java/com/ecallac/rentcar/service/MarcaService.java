/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Marca;

/**
 * @author efrain.calla
 *
 */
public interface MarcaService {
	List<Marca> findList();
	Marca findById(Long id);
	void save(Marca entity);
	void delete(Long id,String deletedBy);
}
