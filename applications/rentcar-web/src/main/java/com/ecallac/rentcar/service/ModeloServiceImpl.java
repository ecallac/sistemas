/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Modelo;
import com.ecallac.rentcar.repository.ModeloRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class ModeloServiceImpl implements ModeloService{

	@Autowired
	ModeloRepository modeloRepository;
	
	@Override
	public List<Modelo> findList() {
		return modeloRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Modelo findById(Long id) {
		return modeloRepository.findById(id).get();
	}

	@Override
	public void save(Modelo entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Modelo dataDB= findById(entity.getId());
			entity = (Modelo) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		modeloRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		modeloRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
