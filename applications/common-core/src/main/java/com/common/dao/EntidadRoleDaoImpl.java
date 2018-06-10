/**
 * 
 */
package com.common.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.common.dao.common.BaseDaoSupport;
import com.common.domain.EntidadRol;
import com.common.domain.TipoBase;
import com.common.utils.CommonConstants;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Repository
public class EntidadRoleDaoImpl extends BaseDaoSupport implements EntidadRoleDao {
	@Autowired
	public EntidadRoleDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public EntidadRol findByEntidadId(Long entidadId,String tipoEntidadRol) {
		String [] params = {"entidadId","tipoEntidadrol"};
		Object [] objects = {entidadId,tipoEntidadRol};
		List<EntidadRol> list = (List<EntidadRol>) getHibernateTemplate().findByNamedParam("FROM EntidadRol WHERE entidad.id = :entidadId and tipoEntidadrol = :tipoEntidadrol", params,objects);
		return CollectionUtils.isNotEmpty(list)?list.get(0):null;
	}
}
