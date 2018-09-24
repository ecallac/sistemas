/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Descripsion;
import com.ecallac.rentcar.repository.DescripsionRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class DescripsionServiceImpl implements DescripsionService {
	
	@Autowired
	DescripsionRepository descripsionRepository;
	@Autowired
	CategoriaService categoriaService;
	
	@Override
	public List<Descripsion> findList() {
		return descripsionRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Descripsion findById(Long id) {
		return descripsionRepository.findById(id).get();
	}

	@Override
	public void save(Descripsion entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Descripsion dataDB= findById(entity.getId());
			entity = (Descripsion) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		entity.setCategoria(categoriaService.findById(entity.getCategoria().getId()));
		descripsionRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		descripsionRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
