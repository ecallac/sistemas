/**
 *
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Empleado;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface EmpleadoService extends BaseService<Empleado> {

    List<Empleado> findByReportaId(Long reportaId);
    List<Empleado> findBySupervisorausenciasId(Long supervisorausenciasId);
    Empleado findFirstByCodigo(String codigo);
    List<Empleado> findByEstado(String estado);
    DataTablesOutput<Empleado> findDataTablesList(DataTablesInput<Empleado> dataTablesInput);
}
