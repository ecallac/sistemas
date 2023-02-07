/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.common.domain.Cargo;
import com.common.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class CargoServiceImpl implements CargoService {

	@Autowired
	CargoRepository cargoRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Cargo> findList() {
		// TODO Auto-generated method stub
		return cargoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Cargo findById(Long id) {
		// TODO Auto-generated method stub
		return cargoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Cargo entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Cargo enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Cargo) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			cargoRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			cargoRepository.save(entity);
		}
	}

	@Override
	public List<Cargo> findByParentCargoId(Long parentCargoId) {
		return cargoRepository.findByParentCargoId(parentCargoId);
	}

	@Override
	public List<Cargo> findByActivo(String activo) {
		return cargoRepository.findByActivo(activo);
	}
}
