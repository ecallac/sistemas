/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Componente;
import com.common.domain.TipoBase;
import com.common.repository.ComponenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class ComponenteServiceImpl extends CommonServiceAbstract<Componente> implements ComponenteService {

	@Autowired
	ComponenteRepository componenteRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Componente> findList() {
		// TODO Auto-generated method stub
		return componenteRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Componente findById(Long id) {
		// TODO Auto-generated method stub
		return componenteRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Componente entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Componente enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Componente) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			componenteRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			componenteRepository.save(entity);
		}
	}

	@Override
	public List<Componente> findByStatus(String status) {
		return componenteRepository.findByStatusOrderByDescripcionAsc(status);
	}
	@Override
	public DataTablesOutput<Componente> findDataTablesList(DataTablesInput<Componente> dataTablesInput) {
			DataTablesOutput dataTablesOutput = super.getSearchedElements(
					componenteRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
					componenteRepository.count());
			dataTablesOutput.setDraw(dataTablesInput.getDraw());
			return dataTablesOutput;
	}
}
