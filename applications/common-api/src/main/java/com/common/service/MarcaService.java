/**
 * 
 */
package com.common.service;

import com.common.domain.Marca;
import com.common.domain.Telefono;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface MarcaService extends BaseService<Marca> {
	List<Marca> findByStatus(String status);
}
