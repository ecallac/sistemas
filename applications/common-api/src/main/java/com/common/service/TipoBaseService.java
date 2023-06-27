/**
 * 
 */
package com.common.service;

import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Componente;
import com.common.domain.TipoBase;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 21:30:05
 */
public interface TipoBaseService {
	void save(TipoBase entity);
	TipoBase findById(Long id);
	List<TipoBase> findAll();
	List<TipoBase> findByCategoria(String categoria);
	List<TipoBase> findByCategoriaActivos(String categoria);
	TipoBase findByCodigo(String codigo);
	DataTablesOutput<TipoBase> findDataTablesList(DataTablesInput<TipoBase> dataTablesInput);
	List<TipoBase> findByDescripcionContaining(String description);
	List<TipoBase> findByCategoriaAndCodigo(String categoria,String codigo);
}
