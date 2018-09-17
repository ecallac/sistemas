/**
 * 
 */
package com.ecallac.rentcar.service;

import com.ecallac.rentcar.domain.Usuario;

/**
 * @author efrain.calla
 *
 */
public interface UsuarioService {
	Usuario findByUsername(String username);
}
