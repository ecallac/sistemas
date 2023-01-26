/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.common.domain.Direccion;
import com.common.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class DireccionServiceImpl implements DireccionService {

	@Autowired
	DireccionRepository direccionRepository; 
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Direccion> findList() {
		// TODO Auto-generated method stub
		return direccionRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Direccion findById(Long id) {
		// TODO Auto-generated method stub
		return direccionRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Direccion entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Direccion enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Direccion) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			direccionRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			direccionRepository.save(entity);
		}
	}

	@Override
	public List<Direccion> findByEntidadIdAndEsprincipal(Long entidadId,String esprincipal) {
		return  direccionRepository.findByEntidadIdAndEsprincipal(entidadId,esprincipal);
	}

	@Override
	public List<Direccion> findByEntidadId(Long entidadId) {
		return direccionRepository.findByEntidadId(entidadId);
	}

}
