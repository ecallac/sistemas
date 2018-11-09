/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Dinero;

/**
 * @author efrain
 *
 */
public interface DineroService {
	List<Dinero> findList();
	Dinero findById(Long id);
	void save(Dinero entity);
	void delete(Long id,String deletedBy);
}
