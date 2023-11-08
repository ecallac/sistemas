/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Sucursal;
import com.common.repository.SucursalRepository;
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
public class SucursalServiceImpl extends CommonServiceAbstract<Sucursal> implements SucursalService {

	@Autowired
	SucursalRepository sucursalRepository; 
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Sucursal> findList() {
		// TODO Auto-generated method stub
		return sucursalRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Sucursal findById(Long id) {
		// TODO Auto-generated method stub
		return sucursalRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Sucursal entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Sucursal enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Sucursal) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			sucursalRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			sucursalRepository.save(entity);
		}
	}


	@Override
	public Sucursal findFirstByNombre(String nombre) {
		return sucursalRepository.findFirstByNombre(nombre);
	}

	@Override
	public List<Sucursal> findByEstado(String estado) {
		return sucursalRepository.findByEstado(estado);
	}

	/**
	 * @param dataTablesInput
	 * @return
	 */
	@Override
	public DataTablesOutput<Sucursal> findDataTablesList(DataTablesInput<Sucursal> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				sucursalRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				sucursalRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}
}
