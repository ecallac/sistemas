/**
 * 
 */
package com.common.services;

import java.util.List;

import com.common.domain.TipoBase;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 21:30:05
 */
public interface TipoBaseService {
	void save(TipoBase tipoBase);
	void updateEnableById(Long id);
	void updateDisableById(Long id);
	TipoBase findTipoBaseById(Long id);
	List<TipoBase> findAllTipoBases();
	List<TipoBase> findTipoBasesXCategoria(String categoria);
	List<TipoBase> findTipoBasesXCategoriaActivos(String categoria);
	
}
