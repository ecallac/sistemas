/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Tipo;
import com.ecallac.rentcar.domain.Trabajo;
import com.ecallac.rentcar.repository.TrabajoRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain
 *
 */
@Service
@Transactional
public class TrabajoServiceImpl implements TrabajoService {
	@Autowired
	TrabajoRepository trabajoRepository;
	@Autowired
	AlquilerService alquilerService;
	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TrabajoService#findList()
	 */
	@Override
	public List<Trabajo> findList() {
		// TODO Auto-generated method stub
		return trabajoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TrabajoService#findById(java.lang.Long)
	 */
	@Override
	public Trabajo findById(Long id) {
		// TODO Auto-generated method stub
		return trabajoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TrabajoService#save(com.ecallac.rentcar.domain.Trabajo)
	 */
	@Override
	public void save(Trabajo entity) {
		// TODO Auto-generated method stub
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
		}else {
			Trabajo dataDB= findById(entity.getId());
			entity = (Trabajo) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		entity.setAlquiler(alquilerService.findById(entity.getAlquiler().getId()));
		trabajoRepository.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TrabajoService#delete(java.lang.Long, java.lang.String)
	 */
	@Override
	public void delete(Long id, String deletedBy) {
		// TODO Auto-generated method stub
		trabajoRepository.deleteById(id);
	}

}
