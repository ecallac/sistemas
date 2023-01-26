/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.common.domain.Area;
import com.common.repository.AreaRepository;
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
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaRepository areaRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Area> findList() {
		// TODO Auto-generated method stub
		return areaRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Area findById(Long id) {
		// TODO Auto-generated method stub
		return areaRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Area entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Area enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Area) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			areaRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			areaRepository.save(entity);
		}
	}

	@Override
	public List<Area> findByParentAreaId(Long parentAreaId) {
		return areaRepository.findByParentAreaId(parentAreaId);
	}

	@Override
	public List<Area> findByActivo(String activo) {
		return areaRepository.findByActivo(activo);
	}
}
