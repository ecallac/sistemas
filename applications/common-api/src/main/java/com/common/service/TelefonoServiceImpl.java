/**
 * 
 */
package com.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BeanParser;
import com.common.domain.Telefono;
import com.common.repository.TelefonoRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class TelefonoServiceImpl implements TelefonoService {

	@Autowired
	TelefonoRepository telefonoRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Telefono> findList() {
		// TODO Auto-generated method stub
		return telefonoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Telefono findById(Long id) {
		// TODO Auto-generated method stub
		return telefonoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Telefono entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Telefono enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
            enrityFromDb = (Telefono) BeanParser.parseBetweenObjects(entity, enrityFromDb);
            enrityFromDb.setDateUpdated(new Date());
            telefonoRepository.save(enrityFromDb);
        }else{
        	entity.setDateCreated(new Date());
        	telefonoRepository.save(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.common.service.TelefonoService#findByEntidadId(java.lang.Long)
	 */
	@Override
	public List<Telefono> findByEntidadId(Long entidadId) {
		// TODO Auto-generated method stub
		return telefonoRepository.findByEntidadId(entidadId);
	}

	/* (non-Javadoc)
	 * @see com.common.service.TelefonoService#findByEntidadIdAndTipo(java.lang.Long, java.lang.String)
	 */
	@Override
	public List<Telefono> findByEntidadIdAndTipo(Long entidadId, String tipo) {
		// TODO Auto-generated method stub
		return telefonoRepository.findByEntidadIdAndTipo(entidadId, tipo);
	}

}
