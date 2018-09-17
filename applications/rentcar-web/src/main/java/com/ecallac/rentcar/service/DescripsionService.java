/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Descripsion;

/**
 * @author efrain.calla
 *
 */
public interface DescripsionService {
	List<Descripsion> findList();
	Descripsion findById(Long id);
	void save(Descripsion entity);
	void delete(Long id,String deletedBy);
}
