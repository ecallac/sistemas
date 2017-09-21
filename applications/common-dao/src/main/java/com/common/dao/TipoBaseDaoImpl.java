/**
 * 
 */
package com.common.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.common.dao.common.BaseDaoSupport;
import com.common.domain.TipoBase;
import com.common.utils.CommonConstants;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 20:04:25
 */
@Transactional
@Repository
public class TipoBaseDaoImpl extends BaseDaoSupport implements TipoBaseDao {
	@Autowired
	public TipoBaseDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoBase> findByTiposBaseXCategorias(String categoria) {
		// TODO Auto-generated method stub
		return (List<TipoBase>) getHibernateTemplate().find("FROM TipoBase WHERE categoria = ?", categoria);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoBase> findByTiposBaseXCategoriasActivas(String categoria) {
		// TODO Auto-generated method stub
		return (List<TipoBase>) getHibernateTemplate().find("FROM TipoBase WHERE activo = ? and categoria = ?", CommonConstants.YES,categoria);
	}

}
