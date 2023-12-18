/**
 * 
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Sucursal;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface SucursalService extends BaseService<Sucursal> {

    Sucursal findFirstByNombre(String nombre);
    List<Sucursal> findByEstadoAndOrganizacionId(String estado,Long organizacionId);
    DataTablesOutput<Sucursal> findDataTablesList(DataTablesInput<Sucursal> dataTablesInput);
}
