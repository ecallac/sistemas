/**
 * 
 */
package com.common.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TipoBaseServiceImpl implements TipoBaseService {

	@Autowired
	TipoBaseRepository tipoBaseRepository;
	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#save(com.common.domain.TipoBase)
	 */
	@Override
	public void save(TipoBase entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			TipoBase enrityFromDb = findById(entity.getId());
            BeanUtils.copyProperties(entity,enrityFromDb);
            tipoBaseRepository.save(enrityFromDb);
        }else{
        	tipoBaseRepository.save(entity);
        }
	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#updateEnableById(java.lang.Long)
	 */
	@Override
	public void updateEnableById(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.common.service.TipoBaseService#updateDisableById(java.lang.Long)
	 */
	@Override
	public void updateDisableById(Long id) {
		// TODO Auto-generated method stub

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

}
