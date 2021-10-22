/**
 * 
 */
package com.common.service;

import java.util.List;

import com.common.domain.TipoBase;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 21:30:05
 */
public interface TipoBaseService {
	void save(TipoBase entity);
	void updateEnableById(Long id);
	void updateDisableById(Long id);
	TipoBase findById(Long id);
	List<TipoBase> findAll();
	List<TipoBase> findByCategoria(String categoria);
	List<TipoBase> findByCategoriaActivos(String categoria);
	TipoBase findByCodigo(String codigo);
	
}
