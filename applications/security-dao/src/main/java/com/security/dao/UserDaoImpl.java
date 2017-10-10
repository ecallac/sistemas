/**
 * 
 */
package com.security.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.common.utils.CommonUtil;
import com.security.dao.common.BaseDaoSupport;
import com.security.domain.User;

/**
 * @author efrain.calla
 *
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository
public class UserDaoImpl extends BaseDaoSupport implements UserDao {
	@Autowired
	public UserDaoImpl(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	public User findUserByUserName(String userName) {
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query qry = session.createQuery("FROM User WHERE userName = ?");
//		qry.setParameter(0, userName);
		List<User> users = (List<User>) getHibernateTemplate().findByNamedParam("FROM User WHERE userName = :userName","userName", userName);
		return CommonUtil.containElemnts(users)?users.get(users.size()-1):null;
	}
	
	public User findUserByUsernameAndPassword(String userName,String password){
		String [] params = {"userName","password"};
		Object [] objects = {userName,password};
		List<User> users = (List<User>) getHibernateTemplate().findByNamedParam("FROM User WHERE userName = :userName and password = :password", params,objects);
		return CommonUtil.containElemnts(users)?users.get(users.size()-1):null;
	}

	public List<User> findUsersByStatus(String status) {
		return (List<User>) getHibernateTemplate().findByNamedParam("FROM User WHERE status = :status","status", status);
	}
}
