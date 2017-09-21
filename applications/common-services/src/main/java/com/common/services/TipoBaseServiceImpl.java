/**
 * 
 */
package com.common.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.TipoBaseDao;
import com.common.domain.TipoBase;
import com.common.utils.CommonConstants;

/**
 * @author EFRAIN
 * @dateCreated 17 sept. 2017 22:02:36
 */
@Transactional
@Service("tipoBaseService")
public class TipoBaseServiceImpl implements TipoBaseService {

	@Autowired
	TipoBaseDao tipoBaseDao;
	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#save(com.common.domain.TipoBase)
	 */
	@Override
	public void save(TipoBase tipoBase) {
		tipoBaseDao.save(tipoBase);

	}

	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#updateEnableById(java.lang.Long)
	 */
	@Override
	public void updateEnableById(Long id) {
		TipoBase tipoBase = findTipoBaseById(id);
		tipoBase.setActivo(CommonConstants.YES);
		save(tipoBase);
	}

	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#updateDisableById(java.lang.Long)
	 */
	@Override
	public void updateDisableById(Long id) {
		TipoBase tipoBase = findTipoBaseById(id);
		tipoBase.setActivo(CommonConstants.NO);
		save(tipoBase);

	}

	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#findModuleById(java.lang.Long)
	 */
	@Override
	public TipoBase findTipoBaseById(Long id) {
		// TODO Auto-generated method stub
		return (TipoBase) tipoBaseDao.findById(TipoBase.class, id);
	}

	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#findAllTipoBases()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TipoBase> findAllTipoBases() {
		// TODO Auto-generated method stub
		return (List<TipoBase>) tipoBaseDao.findAll(TipoBase.class);
	}

	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#findTipoBasesXCategoria(java.lang.String)
	 */
	@Override
	public List<TipoBase> findTipoBasesXCategoria(String categoria) {
		// TODO Auto-generated method stub
		return tipoBaseDao.findByTiposBaseXCategorias(categoria);
	}

	/* (non-Javadoc)
	 * @see com.common.services.TipoBaseService#findTipoBasesXCategoriaActivos(java.lang.String)
	 */
	@Override
	public List<TipoBase> findTipoBasesXCategoriaActivos(String categoria) {
		// TODO Auto-generated method stub
		return tipoBaseDao.findByTiposBaseXCategoriasActivas(categoria);
	}

}
