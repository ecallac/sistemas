/**
 * 
 */
package com.common.dao;

import java.util.List;

import com.common.dao.common.BaseDao;
import com.common.domain.TipoBase;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 19:55:57
 */
public interface TipoBaseDao extends BaseDao {
	List<TipoBase> findByTiposBaseXCategorias(String categoria);
	List<TipoBase> findByTiposBaseXCategoriasActivas(String categoria);
	TipoBase findByTiposBaseXCodigo(String codigo);
}
