/**
 * 
 */
package com.common.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.common.dao.common.BaseDaoSupport;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Repository
public class EntidadDaoImpl extends BaseDaoSupport implements EntidadDao {
	@Autowired
	public EntidadDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
