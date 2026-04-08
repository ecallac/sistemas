/**
 * 
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Producto;

/**
 * @author efrain.calla
 *
 */
public interface ProductoService extends BaseService<Producto> {
	Producto findByNombre(String nombre);
	DataTablesOutput<Producto> findDataTablesList(DataTablesInput<Producto> dataTablesInput);
}
