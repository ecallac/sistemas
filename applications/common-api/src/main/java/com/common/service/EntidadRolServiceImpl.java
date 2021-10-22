/**
 * 
 */
package com.common.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BeanParser;
import com.common.domain.EntidadRol;
import com.common.repository.EntidadRolRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class EntidadRolServiceImpl implements EntidadRolService {

	@Autowired
	EntidadRolRepository entidadRolRepository;
	/* (non-Javadoc)
	 * @see com.common.service.EntidadRolService#findById(java.lang.Long)
	 */
	@Override
	public EntidadRol findById(Long id) {
		// TODO Auto-generated method stub
		return entidadRolRepository.findById(id).get();
	}
	@Override
	public void save(EntidadRol entidadRol) {
		if (entidadRol.getId()!=null){
			EntidadRol enrityFromDb = findById(entidadRol.getId());
            entidadRol.setVersion(enrityFromDb.getVersion()+1);
            enrityFromDb = (EntidadRol) BeanParser.parseBetweenObjects(entidadRol, enrityFromDb);
            enrityFromDb.setDateUpdated(new Date());
            entidadRolRepository.save(enrityFromDb);
        }else{
        	entidadRol.setDateCreated(new Date());
        	entidadRolRepository.save(entidadRol);
        }
	}

}
