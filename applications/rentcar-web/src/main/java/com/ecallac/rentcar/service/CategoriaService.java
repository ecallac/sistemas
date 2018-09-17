/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Categoria;

/**
 * @author efrain.calla
 *
 */
public interface CategoriaService {
	List<Categoria> findList();
	Categoria findById(Long id);
	void save(Categoria entity);
	void delete(Long id,String deletedBy);
}
