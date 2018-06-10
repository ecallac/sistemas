/**
 * 
 */
package com.common.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.TelefonoDao;
import com.common.domain.Telefono;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Service("telefonoService")
public class TelefonoServiceImpl implements TelefonoService {

	@Autowired
	TelefonoDao telefonoDao;
	
	/* (non-Javadoc)
	 * @see com.common.services.TelefonoService#save(com.common.domain.Telefono)
	 */
	@Override
	public void save(Telefono telefono) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.common.services.TelefonoService#findById(java.lang.Long)
	 */
	@Override
	public Telefono findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.common.services.TelefonoService#findAll()
	 */
	@Override
	public List<Telefono> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.common.services.TelefonoService#findByEntidadId(java.lang.Long)
	 */
	@Override
	public List<Telefono> findByEntidadId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
