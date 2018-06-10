/**
 * 
 */
package com.common.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.ReglaDao;
import com.common.domain.Regla;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Service("reglaService")
public class ReglaServiceImpl implements ReglaService {

	@Autowired
	ReglaDao reglaDao;
	
	@Override
	public void save(Regla regla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Regla findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Regla> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
