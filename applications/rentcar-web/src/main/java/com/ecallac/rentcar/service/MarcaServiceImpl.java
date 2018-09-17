/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Marca;
import com.ecallac.rentcar.repository.MarcaRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	MarcaRepository marcaRepository;
	
	@Override
	public List<Marca> findList() {
		return marcaRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Marca findById(Long id) {
		return marcaRepository.findById(id).get();
	}

	@Override
	public void save(Marca entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Marca dataDB= findById(entity.getId());
			entity = (Marca) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		marcaRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		marcaRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
