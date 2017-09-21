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
		List<User> users = (List<User>) getHibernateTemplate().find("FROM User WHERE userName = ?", userName);
		return CommonUtil.containElemnts(users)?users.get(users.size()-1):null;
	}
	
	public User findUserByUsernameAndPassword(String userName,String password){
		List<User> users = (List<User>) getHibernateTemplate().find("FROM User WHERE userName = ? and password = ?", userName,password);
		return CommonUtil.containElemnts(users)?users.get(users.size()-1):null;
	}

	public List<User> findUsersByStatus(String status) {
		return (List<User>) getHibernateTemplate().find("FROM User WHERE status = ?", status);
	}
}
