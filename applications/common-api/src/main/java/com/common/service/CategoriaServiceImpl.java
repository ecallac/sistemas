/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Categoria;
import com.common.repository.CategoriaRepository;
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
public class CategoriaServiceImpl extends CommonServiceAbstract<Categoria> implements CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Categoria> findList() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Categoria findById(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Categoria entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Categoria enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Categoria) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			categoriaRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			categoriaRepository.save(entity);
		}
	}

	@Override
	public List<Categoria> findByParentCategoriaId(Long parentCategoriaId) {
		return categoriaRepository.findByCategoriapadreId(parentCategoriaId);
	}

	@Override
	public List<Categoria> findByStatus(String status) {
		return categoriaRepository.findByStatus(status);
	}

	/**
	 * @param nombre
	 * @return
	 */
	@Override
	public Categoria findByNombre(String nombre) {
		return categoriaRepository.findFirstByNombre(nombre);
	}

	/**
	 * @param dataTablesInput
	 * @return
	 */
	@Override
	public DataTablesOutput<Categoria> findDataTablesList(DataTablesInput<Categoria> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				categoriaRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				categoriaRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}
}
