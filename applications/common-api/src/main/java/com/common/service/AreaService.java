/**
 *
 */
package com.common.service;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Area;
import com.common.domain.TipoBase;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface AreaService extends BaseService<Area> {

    List<Area> findByParentAreaId(Long parentAreaId);
    List<Area> findByActivo(String activo);
    Area findByNombre(String nombre);
    DataTablesOutput<Area> findDataTablesList(DataTablesInput<Area> dataTablesInput);
}
