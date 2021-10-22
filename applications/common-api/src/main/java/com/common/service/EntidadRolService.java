/**
 * 
 */
package com.common.service;

import com.common.domain.EntidadRol;

/**
 * @author efrain.calla
 *
 */
public interface EntidadRolService {
	EntidadRol findById(Long id);
	void save(EntidadRol entidadRol);
}
