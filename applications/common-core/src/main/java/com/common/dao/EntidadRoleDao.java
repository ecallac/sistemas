/**
 * 
 */
package com.common.dao;

import com.common.dao.common.BaseDao;
import com.common.domain.EntidadRol;

/**
 * @author efrain.calla
 *
 */
public interface EntidadRoleDao extends BaseDao {
	EntidadRol findByEntidadId(Long entidadId,String tipoEntidadRol);
}
