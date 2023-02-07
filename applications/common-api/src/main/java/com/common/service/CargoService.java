/**
 *
 */
package com.common.service;

import com.common.domain.Cargo;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface CargoService extends BaseService<Cargo> {

    List<Cargo> findByParentCargoId(Long parentCargoId);
    List<Cargo> findByActivo(String activo);
}
