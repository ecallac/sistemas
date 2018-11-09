/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Alquiler;
import com.ecallac.rentcar.domain.Vehiculo;
import com.ecallac.rentcar.repository.AlquilerRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain
 *
 */
@Service
@Transactional
public class AlquilerServiceImpl implements AlquilerService{
	@Autowired
	AlquilerRepository alquilerRepository;
	@Autowired
	ConductorService conductorService;
	@Autowired
	VehiculoService vehiculoService;
	
	@Override
	public List<Alquiler> findList() {
		List<String> status = new ArrayList<String>();
		status.add(Status.ENABLED.getCode());
		status.add(Status.NEW.getCode());
		return alquilerRepository.findAllByStatusIn(status);
	}

	@Override
	public Alquiler findById(Long id) {
		return alquilerRepository.findById(id).get();
	}

	@Override
	public void save(Alquiler entity) {
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.NEW.getCode());
		}else {
			Alquiler dataDB= findById(entity.getId());
			entity = (Alquiler) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		entity.setConductor(conductorService.findById(entity.getConductor().getId()));
		entity.setVehiculo(vehiculoService.findById(entity.getVehiculo().getId()));
		alquilerRepository.save(entity);
	}

	@Override
	public void delete(Long id,String deletedBy) {
		alquilerRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

	@Override
	public void updateStatus(Long id, String status, String changedBy) {
		alquilerRepository.updateStatus(status,changedBy,new Date(), id);
	}

	@Override
	public Alquiler findByVehiculoIdAndFecha(Long vehiculoId, Date date) {
		return alquilerRepository.findAllByVehiculoIdAndFecha(vehiculoId, date).get();
	}
}
