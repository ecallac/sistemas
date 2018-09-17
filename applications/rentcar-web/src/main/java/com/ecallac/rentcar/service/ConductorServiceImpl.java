/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Conductor;
import com.ecallac.rentcar.repository.ConductorRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class ConductorServiceImpl implements ConductorService {
	
	@Autowired
	ConductorRepository conductorRepository;
	
	@Override
	public List<Conductor> findList() {
		return conductorRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Conductor findById(Long id) {
		return conductorRepository.findById(id).get();
	}

	@Override
	public void save(Conductor entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
//			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Conductor dataDB= findById(entity.getId());
			entity = (Conductor) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		conductorRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		conductorRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
