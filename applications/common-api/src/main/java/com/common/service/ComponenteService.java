/**
 * 
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Componente;
import com.common.domain.Marca;
import com.common.domain.TipoBase;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface ComponenteService extends BaseService<Componente> {
	List<Componente> findByStatus(String status);
	DataTablesOutput<Componente> findDataTablesList(DataTablesInput<Componente> dataTablesInput);
}
