/**
 * 
 */
package com.ecallac.rentcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecallac.rentcar.domain.Usuario;
import com.ecallac.rentcar.repository.UsuarioRepository;

/**
 * @author efrain.calla
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findAllByUsername(username);
	}

}
