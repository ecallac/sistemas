/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.common.domain.EntidadRolAtributo;
import com.common.repository.EntidadRolAtributoRepository;
import com.common.repository.EntidadRolAtributoRepository;
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
public class EntidadRolAtributoServiceImpl implements EntidadRolAtributoService {

	@Autowired
	EntidadRolAtributoRepository entidadRolAtributoRepository; 
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<EntidadRolAtributo> findList() {
		// TODO Auto-generated method stub
		return entidadRolAtributoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public EntidadRolAtributo findById(Long id) {
		// TODO Auto-generated method stub
		return entidadRolAtributoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(EntidadRolAtributo entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			EntidadRolAtributo enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (EntidadRolAtributo) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			entidadRolAtributoRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			entidadRolAtributoRepository.save(entity);
		}
	}

	@Override
	public List<EntidadRolAtributo> findByEntidadRolId(Long entidadRolId) {
		return entidadRolAtributoRepository.findByEntidadRolId(entidadRolId);
	}
}
