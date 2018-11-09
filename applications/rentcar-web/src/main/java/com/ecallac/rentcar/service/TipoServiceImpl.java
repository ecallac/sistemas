/**
 * 
 */
package com.ecallac.rentcar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecallac.rentcar.domain.Tipo;
import com.ecallac.rentcar.repository.TipoRepository;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Status;

/**
 * @author efrain
 *
 */
@Service
@Transactional
public class TipoServiceImpl implements TipoService {
	@Autowired
	TipoRepository tipoRepository;
	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TipoService#findList()
	 */
	@Override
	public List<Tipo> findList() {
		// TODO Auto-generated method stub
		return tipoRepository.findAllByStatus(Status.ENABLED.getCode());
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TipoService#findById(java.lang.Long)
	 */
	@Override
	public Tipo findById(Long id) {
		// TODO Auto-generated method stub
		return tipoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TipoService#save(com.ecallac.rentcar.domain.Tipo)
	 */
	@Override
	public void save(Tipo entity) {
		// TODO Auto-generated method stub
		if (entity.getId()==null) {
			entity.setDateCreated(new Date());
			entity.setStatus(Status.ENABLED.getCode());
		}else {
			Tipo dataDB= findById(entity.getId());
			entity = (Tipo) BeanParser.parseBetweenObjects(entity, dataDB, null);
			entity.setDateUpdated(new Date());
			entity.setVersion(entity.getVersion()+1);
		}
		tipoRepository.save(entity);
	}

	/* (non-Javadoc)
	 * @see com.ecallac.rentcar.service.TipoService#delete(java.lang.Long, java.lang.String)
	 */
	@Override
	public void delete(Long id, String deletedBy) {
		// TODO Auto-generated method stub
		tipoRepository.updateStatus(Status.DELETED.getCode(),deletedBy,new Date(), id);
	}

}
