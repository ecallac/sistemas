/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Marca;
import com.common.repository.MarcaRepository;
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
public class MarcaServiceImpl extends CommonServiceAbstract<Marca> implements MarcaService {

	@Autowired
	MarcaRepository marcaRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Marca> findList() {
		// TODO Auto-generated method stub
		return marcaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Marca findById(Long id) {
		// TODO Auto-generated method stub
		return marcaRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Marca entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Marca enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Marca) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			marcaRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			marcaRepository.save(entity);
		}
	}

	@Override
	public List<Marca> findByStatus(String status) {
		return marcaRepository.findByStatusOrderByDescripcionAsc(status);
	}

	@Override
	public Marca findByNombre(String nombre) {
		return marcaRepository.findFirstByNombre(nombre);
	}

	@Override
	public DataTablesOutput<Marca> findDataTablesList(DataTablesInput<Marca> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				marcaRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				marcaRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}
}
