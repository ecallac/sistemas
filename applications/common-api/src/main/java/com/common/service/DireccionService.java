/**
 *
 */
package com.common.service;

import com.common.domain.Direccion;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface DireccionService extends BaseService<Direccion> {

    List<Direccion> findByEntidadIdAndEsprincipal(Long entidadId,String esprincipal);
    List<Direccion> findByEntidadId(Long entidadId);
    List<Direccion> findByEstadoAndEntidadId(String estado,Long entidadId);

}
