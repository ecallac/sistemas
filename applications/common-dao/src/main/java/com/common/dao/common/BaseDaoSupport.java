/**
 * 
 */
package com.common.dao.common;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.common.domain.BaseEntity;
import com.common.utils.CommonUtil;

/**
 * @author efrain.calla
 *
 */
public class BaseDaoSupport extends HibernateDaoSupport {
	
	public void save(BaseEntity baseEntity) {
		Date systemDate = new Date();
		if (baseEntity.getId() == null) {
			baseEntity.setDateCreated(systemDate);
		}
		baseEntity.setDateUpdated(systemDate);
		getHibernateTemplate().saveOrUpdate(baseEntity);
	}
	
	public void deleteList(List<BaseEntity> baseEntities) {
		getHibernateTemplate().deleteAll(baseEntities);
	}
	
	public void delete(BaseEntity baseEntity) {
		getHibernateTemplate().delete(baseEntity);
	}
	public Object findById(Class<?> classObject, Long id) {
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query qry = session.createQuery("FROM BaseEntity WHERE id = ?");
//		qry.setParameter(0, id);
//		List<BaseEntity> list = qry.list();
		String query = "FROM "+classObject.getName()+" WHERE id = ?";
		List<?> list = (List<?>) getHibernateTemplate().find(query, id);
		return CommonUtil.containElemnts(list)?list.get(list.size()-1):null;
	}

	public List<?> findAll(Class<?> classObject) {
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query qry = session.createQuery("FROM BaseEntity");
		return getHibernateTemplate().find("FROM "+classObject.getName());
//		return qry.list();
	}
}
