/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Cierre;
import com.ecallac.rentcar.domain.Clase;
import com.ecallac.rentcar.repository.CierreRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain
 *
 */
@Service
@Transactional
public class CierreServiceImpl implements CierreService {

	@Autowired
	CierreRepository cierreRepository;
	@Autowired
	VehiculoService vehiculoService;
	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.CierreService#findList()
	 */
	@Override
	public List<Cierre> findList() {
		// TODO Auto-generated method stub
		return cierreRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.CierreService#findById(java.lang.Long)
	 */
	@Override
	public Cierre findById(Long id) {
		// TODO Auto-generated method stub
		return cierreRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.CierreService#save(com.ecallac.rentcar.domain.Cierre)
	 */
	@Override
	public void save(Cierre entity) {
		// TODO Auto-generated method stub
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
		}else {
			Cierre dataDB= findById(entity.getId());
			entity = (Cierre) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		entity.setVehiculo(vehiculoService.findById(entity.getVehiculo().getId()));
		cierreRepository.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.CierreService#delete(java.lang.Long, java.lang.String)
	 */
	@Override
	public void delete(Long id, String deletedBy) {
		// TODO Auto-generated method stub
		cierreRepository.deleteById(id);
	}

}
