/**
 * 
 */
package com.common.service;

import com.common.domain.EntidadRolAtributo;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface EntidadRolAtributoService extends BaseService<EntidadRolAtributo>{
    List<EntidadRolAtributo> findByEntidadRolId(Long entidadRolId);
}
