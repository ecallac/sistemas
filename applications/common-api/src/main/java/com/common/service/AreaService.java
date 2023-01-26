/**
 *
 */
package com.common.service;

import com.common.domain.Area;
import com.common.domain.Area;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface AreaService extends BaseService<Area> {

    List<Area> findByParentAreaId(Long parentAreaId);
    List<Area> findByActivo(String activo);
}
