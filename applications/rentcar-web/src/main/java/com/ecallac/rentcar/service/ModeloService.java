/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Modelo;

/**
 * @author efrain.calla
 *
 */
public interface ModeloService {
	List<Modelo> findList();
	Modelo findById(Long id);
	void save(Modelo entity);
	void delete(Long id,String deletedBy);
	List<Modelo> findListByMarca(Long marcaId);
}
