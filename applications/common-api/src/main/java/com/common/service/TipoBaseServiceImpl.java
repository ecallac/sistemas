/**
 * 
 */
package com.common.service;

import java.util.Date;
import java.util.List;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Cargo;
import com.common.domain.Componente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.domain.TipoBase;
import com.common.enums.EnableIndicator;
import com.common.repository.TipoBaseRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class TipoBaseServiceImpl extends CommonServiceAbstract<TipoBase> implements TipoBaseService {

	@Autowired
	TipoBaseRepository tipoBaseRepository;
	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#save(com.common.domain.TipoBase)
	 */
	@Override
	public void save(TipoBase entity) {
		if (entity.getId()!=null){
			TipoBase enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (TipoBase) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			tipoBaseRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			tipoBaseRepository.save(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#findById(java.lang.Long)
	 */
	@Override
	public TipoBase findById(Long id) {
		// TODO Auto-generated method stub
		return tipoBaseRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#findAll()
	 */
	@Override
	public List<TipoBase> findAll() {
		// TODO Auto-generated method stub
		return tipoBaseRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#findByCategoria(java.lang.String)
	 */
	@Override
	public List<TipoBase> findByCategoria(String categoria) {
		// TODO Auto-generated method stub
		return tipoBaseRepository.findByCategoria(categoria);
	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#findByCategoriaActivos(java.lang.String)
	 */
	@Override
	public List<TipoBase> findByCategoriaActivos(String categoria) {
		// TODO Auto-generated method stub
		return tipoBaseRepository.findByCategoriaAndActivo(categoria, EnableIndicator.ENABLED.getCode());
	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#findByCodigo(java.lang.String)
	 */
	@Override
	public TipoBase findByCodigo(String codigo) {
		// TODO Auto-generated method stub
		return tipoBaseRepository.findFirstByCodigo(codigo);
	}

	@Override
	public DataTablesOutput<TipoBase> findDataTablesList(DataTablesInput<TipoBase> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				tipoBaseRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				tipoBaseRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}

	@Override
	public List<TipoBase> findByDescripcionContaining(String description) {
		return tipoBaseRepository.findByDescripcionIgnoreCaseContaining(description);
	}

	/**
	 * @param categoria
	 * @param codigo
	 * @return
	 */
	@Override
	public List<TipoBase> findByCategoriaAndCodigo(String categoria, String codigo) {
		return tipoBaseRepository.findByCategoriaAndCodigo(categoria, codigo);
	}
}
