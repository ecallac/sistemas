/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Cierre;

/**
 * @author efrain
 *
 */
public interface CierreService {
	List<Cierre> findList();
	Cierre findById(Long id);
	void save(Cierre entity);
	void delete(Long id,String deletedBy);
}
