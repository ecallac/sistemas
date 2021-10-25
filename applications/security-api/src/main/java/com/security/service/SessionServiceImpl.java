/**
 * 
 */
package com.security.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.domain.Session;
import com.security.repository.SessionRepository;
import com.security.util.BeanParser;

/**
 * @author efrain.calla
 *
 */
@Service
public class SessionServiceImpl implements SessionService{

	@Autowired
	SessionRepository sessionRepository;
	@Override
	public List<Session> findList() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll();
	}

	@Override
	public Session findById(Long id) {
		// TODO Auto-generated method stub
		return sessionRepository.findById(id).get();
	}

	@Override
	public void save(Session entity) {
		if (entity.getId()!=null){
			Session enrityFromDb = findById(entity.getId());
            enrityFromDb = (Session) BeanParser.parseBetweenObjects(entity, enrityFromDb);
//            enrityFromDb.setDateUpdated(new Date());
            sessionRepository.save(enrityFromDb);
        }else{
        	entity.setLoginDate(new Date());
        	sessionRepository.save(entity);
        }
	}

	@Override
	public Session findBySessionKey(String sessionKey) {
		// TODO Auto-generated method stub
		return sessionRepository.findBySessionKey(sessionKey);
	}

}
