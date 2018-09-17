/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Categoria;
import com.ecallac.rentcar.repository.CategoriaRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Override
	public List<Categoria> findList() {
		return categoriaRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).get();
	}

	@Override
	public void save(Categoria entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Categoria dataDB= findById(entity.getId());
			entity = (Categoria) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		categoriaRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		categoriaRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
