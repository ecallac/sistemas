/**
 * 
 */
package com.security.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.security.dao.common.BaseDaoSupport;
import com.security.domain.Permission;

/**
 * @author EFRAIN
 * @dateCreated 26 mar. 2017 9:16:58
 */
@Transactional
@Repository
public class PermissionDaoImpl extends BaseDaoSupport implements PermissionDao {
	@Autowired
	public PermissionDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<Permission> findByEnabled(String enabled) {
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query qry = session.createQuery("FROM Permission WHERE status = ?");
//		qry.setParameter(0, id);
		return (List<Permission>) getHibernateTemplate().find("FROM Permission WHERE enabled = ?", enabled);
	}

	@SuppressWarnings("unchecked")
	public List<Permission> findByModuleId(Long id) {
		return (List<Permission>) getHibernateTemplate().find("FROM Permission WHERE module.id = ?", id);
	}

	@SuppressWarnings("unchecked")
	public List<Permission> findPermissionByRoleId(Long id) {
		List<Permission> permissions = (List<Permission>) getHibernateTemplate().find("SELECT p FROM Permission p join p.roles r WHERE r.id = ?", id);
//		List<Permission> permissions = new ArrayList<Permission>();
//		for (RolePermission rolePermission : rolePermissions) {
//			permissions.add(rolePermission.getPermission());
//		}
		return permissions;
	}
}
