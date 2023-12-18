/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.common.domain.Ubigeo;
import com.common.repository.UbigeoRepository;
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
public class UbigeoServiceImpl implements UbigeoService {

	@Autowired
	UbigeoRepository ubigeoRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Ubigeo> findList() {
		// TODO Auto-generated method stub
		return ubigeoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Ubigeo findById(Long id) {
		// TODO Auto-generated method stub
		return ubigeoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Ubigeo entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Ubigeo enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Ubigeo) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			ubigeoRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			ubigeoRepository.save(entity);
		}
	}

	@Override
	public List<Ubigeo> findByParentUbigeoIdAndEstado(Long parentUbigeoId,String estado) {
		return ubigeoRepository.findByParentUbigeoIdAndEstadoOrderByDescripcionAsc(parentUbigeoId,estado);
	}
}
