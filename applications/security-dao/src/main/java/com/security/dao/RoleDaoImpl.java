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
import com.security.domain.Role;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 12:54:14
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository
public class RoleDaoImpl extends BaseDaoSupport implements RoleDao {

	@Autowired
	public RoleDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	public List<Role> findRolesByUserName(String userName) {
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query qry = session.createQuery("FROM RoleUser WHERE user.userName = ?");
//		qry.setParameter(0, userName);
		List<Role> roles = (List<Role>) getHibernateTemplate().find("SELECT r FROM Role r join r.users u WHERE u.userName = ?", userName);
//		List<Role> roles = new ArrayList<Role>();
//		for (RoleUser roleUser : roleUsers) {
//			roles.add(roleUser.getRole());
//		}
		return roles;
	}

	public List<Role> findRolesByUserId(Long id) {
		List<Role> roles = (List<Role>) getHibernateTemplate().find("SELECT r FROM Role r join r.users u WHERE u.id = ?", id);
//		List<RoleUser> roleUsers = (List<RoleUser>) getHibernateTemplate().find("FROM RoleUser WHERE user.id = ?", id);
//		List<Role> roles = new ArrayList<Role>();
//		for (RoleUser roleUser : roleUsers) {
//			roles.add(roleUser.getRole());
//		}
		return roles;
	}


}
