/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Regla;
import com.common.repository.ReglaRepository;
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
public class ReglaServiceImpl extends CommonServiceAbstract<Regla> implements ReglaService {

	@Autowired
	ReglaRepository reglaRepository; 
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Regla> findList() {
		// TODO Auto-generated method stub
		return reglaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Regla findById(Long id) {
		// TODO Auto-generated method stub
		return reglaRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Regla entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Regla enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Regla) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			reglaRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			reglaRepository.save(entity);
		}
	}

	@Override
	public Regla findByCodigo(String codigo) {
		return reglaRepository.findFirstByCodigo(codigo);
	}

	@Override
	public List<Regla> findByActivo(String activo) {
		return reglaRepository.findByActivo(activo);
	}

	/**
	 * @param dataTablesInput
	 * @return
	 */
	@Override
	public DataTablesOutput<Regla> findDataTablesList(DataTablesInput<Regla> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				reglaRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				reglaRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}
}
