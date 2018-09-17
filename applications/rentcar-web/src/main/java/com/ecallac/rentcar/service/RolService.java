/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.List;

import com.ecallac.rentcar.domain.Rol;

/**
 * @author efrain.calla
 *
 */
public interface RolService {
	List<Rol> findAll();
	List<Rol> findAllByStatus(String status);
}
