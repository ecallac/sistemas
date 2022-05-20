/**
 * 
 */
package com.common.service;

import java.util.List;

import com.common.domain.Telefono;

/**
 * @author efrain.calla
 *
 */
public interface TelefonoService extends BaseService<Telefono> {
	List<Telefono> findByEntidadId(Long entidadId);
	List<Telefono> findByEntidadIdAndTipo(Long entidadId,String tipo);
}
