/**
 * 
 */
package com.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.domain.ReglaDetalle;
import com.common.repository.ReglaDetalleRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class ReglaDetalleServiceImpl implements ReglaDetalleService {

	@Autowired
	ReglaDetalleRepository reglaDetalleRepository; 
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<ReglaDetalle> findList() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public ReglaDetalle findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(ReglaDetalle entity) {
		// TODO Auto-generated method stub

	}

}