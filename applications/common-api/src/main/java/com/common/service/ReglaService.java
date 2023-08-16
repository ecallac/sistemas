/**
 * 
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Regla;
import com.common.domain.ReglaDetalle;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface ReglaService extends BaseService<Regla> {

    Regla findByCodigo(String codigo);
    List<Regla> findByActivo(String activo);
    DataTablesOutput<Regla> findDataTablesList(DataTablesInput<Regla> dataTablesInput);
}
