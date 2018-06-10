/**
 * 
 */
package com.common.services;

import java.util.List;

import com.common.domain.Regla;

/**
 * @author efrain.calla
 *
 */
public interface ReglaService {
	void save(Regla regla);
	Regla findById(Long id);
	List<Regla> findAll();
}
