/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
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
public class CargoServiceImpl extends CommonServiceAbstract<Cargo> implements CargoService {

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

	@Override
	public Cargo findByNombre(String nombre) {
		return cargoRepository.findFirstByNombre(nombre);
	}

	@Override
	public DataTablesOutput<Cargo> findDataTablesList(DataTablesInput<Cargo> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				cargoRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				cargoRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}
}
