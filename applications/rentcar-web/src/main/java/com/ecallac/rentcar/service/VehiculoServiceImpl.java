/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Vehiculo;
import com.ecallac.rentcar.repository.VehiculoRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	@Autowired
	ModeloService modeloService;
	@Autowired
	ClaseService claseService;
	
	@Override
	public List<Vehiculo> findList() {
		return vehiculoRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	@Override
	public Vehiculo findById(Long id) {
		return vehiculoRepository.findById(id).get();
	}

	@Override
	public void save(Vehiculo entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Vehiculo dataDB= findById(entity.getId());
			entity = (Vehiculo) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		entity.setModelo(modeloService.findById(entity.getModelo().getId()));
		entity.setClase(claseService.findById(entity.getClase().getId()));
		vehiculoRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		vehiculoRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
