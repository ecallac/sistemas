/**
 *
 */
package com.common.service;

import com.common.domain.Ubigeo;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
public interface UbigeoService extends BaseService<Ubigeo> {

    List<Ubigeo> findByParentUbigeoIdAndEstado(Long parentUbigeoId,String estado);

}
