/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Dinero;
import com.ecallac.rentcar.repository.DineroRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain
 *
 */
@Service
@Transactional
public class DineroServiceImpl implements DineroService {

	@Autowired
	DineroRepository dineroRepository;
	@Autowired
	TipoService tipoService;
	@Autowired
	VehiculoService vehiculoService;
	@Autowired
	DescripsionService descripsionService;
	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.DineroService#findList()
	 */
	@Override
	public List<Dinero> findList() {
		// TODO Auto-generated method stub
		return dineroRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.DineroService#findById(java.lang.Long)
	 */
	@Override
	public Dinero findById(Long id) {
		// TODO Auto-generated method stub
		return dineroRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.DineroService#save(com.ecallac.rentcar.domain.Dinero)
	 */
	@Override
	public void save(Dinero entity) {
		// TODO Auto-generated method stub
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
//			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Dinero dataDB= findById(entity.getId());
			entity = (Dinero) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		entity.setTipo(tipoService.findById(entity.getTipo().getId()));
		entity.setVehiculo(vehiculoService.findById(entity.getVehiculo().getId()));
		entity.setDescripsion(descripsionService.findById(entity.getDescripsion().getId()));
		dineroRepository.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.DineroService#delete(java.lang.Long, java.lang.String)
	 */
	@Override
	public void delete(Long id, String deletedBy) {
		// TODO Auto-generated method stub
		dineroRepository.deleteById(id);
	}

}
