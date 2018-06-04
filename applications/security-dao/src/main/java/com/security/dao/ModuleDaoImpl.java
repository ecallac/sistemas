/**
 * 
 */
package com.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.security.dao.common.BaseDaoSupport;
import com.security.domain.Module;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 9:16:58
 */
@Transactional
@Repository
public class ModuleDaoImpl extends BaseDaoSupport implements ModuleDao {
	@Autowired
	public ModuleDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Module> findByEnabled(String enabled){
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query qry = session.createQuery("FROM Module WHERE status = ?");
//		qry.setParameter(0, id);
		return (List<Module>) getHibernateTemplate().findByNamedParam("FROM Module WHERE enabled = :enabled","enabled", enabled);
	}

	@SuppressWarnings("unchecked")
	public Module findByName(String name) {
		// TODO Auto-generated method stub
		List<Module> modules = (List<Module>) getHibernateTemplate().findByNamedParam("FROM Module WHERE name = :name", "name", name);
		return CollectionUtils.isNotEmpty(modules)?modules.get(modules.size()-1):null;
	}
}
