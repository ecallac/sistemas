/**
 * 
 */
package com.common.service;

import com.common.domain.ReglaDetalle;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface ReglaDetalleService extends BaseService<ReglaDetalle> {

    List<ReglaDetalle> findByReglaCategoria(String categoria);
    List<ReglaDetalle> findByReglaCodigo(String codigo);
}
