/**
 * 
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Marca;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface MarcaService extends BaseService<Marca> {
	List<Marca> findByStatus(String status);
	Marca findByNombre(String nombre);
	DataTablesOutput<Marca> findDataTablesList(DataTablesInput<Marca> dataTablesInput);
}
