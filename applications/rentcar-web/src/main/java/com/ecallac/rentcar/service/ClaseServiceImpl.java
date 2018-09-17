/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Clase;
import com.ecallac.rentcar.repository.ClaseRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class ClaseServiceImpl implements ClaseService {
	
	@Autowired
	ClaseRepository claseRepository;
	
	@Override
	public List<Clase> findList() {
		return claseRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Clase findById(Long id) {
		return claseRepository.findById(id).get();
	}

	@Override
	public void save(Clase entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Clase dataDB= findById(entity.getId());
			entity = (Clase) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		claseRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		claseRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
