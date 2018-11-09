/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Trabajo;

/**
 * @author efrain
 *
 */
public interface TrabajoService {
	List<Trabajo> findList();
	Trabajo findById(Long id);
	void save(Trabajo entity);
	void delete(Long id,String deletedBy);
}
