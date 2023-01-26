/**
 * 
 */
package com.common.service;

import java.util.Date;
import java.util.List;

import com.BeanParser;
import com.common.domain.Telefono;
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
		return reglaDetalleRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public ReglaDetalle findById(Long id) {
		// TODO Auto-generated method stub
		return reglaDetalleRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(ReglaDetalle entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			ReglaDetalle enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (ReglaDetalle) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			reglaDetalleRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			reglaDetalleRepository.save(entity);
		}
	}

	@Override
	public List<ReglaDetalle> findByReglaCategoria(String categoria) {
		return reglaDetalleRepository.findByReglaCategoria(categoria);
	}

	@Override
	public List<ReglaDetalle> findByReglaCodigo(String codigo) {
		return reglaDetalleRepository.findByReglaCodigo(codigo);
	}
}
