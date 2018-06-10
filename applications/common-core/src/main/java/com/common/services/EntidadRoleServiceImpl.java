/**
 * 
 */
package com.common.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.EntidadRoleDao;
import com.common.domain.Entidad;
import com.common.domain.EntidadRol;
import com.common.domain.TipoBase;
import com.common.utils.BusinessException;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Service("entidadRoleService")
public class EntidadRoleServiceImpl implements EntidadRoleService {
	
	@Autowired
	private EntidadService entidadService;
	
	@Autowired
	private EntidadRoleDao entidadRoleDao;
	
	@Autowired
	private TipoBaseService tipoBaseService;
	
	@Override
	public void save(EntidadRol entidadRol) throws BusinessException {
		Entidad entidad = entidadService.findById(entidadRol.getEntidad().getId());
		if (entidad==null) {
			throw new BusinessException("There isn't an entity with id: " + entidadRol.getEntidad().getId());
		}
		entidadRol.setEntidad(entidad);
		entidad.getEntidadRols().add(entidadRol);
		TipoBase tipoBase = tipoBaseService.findTipoBaseById(Long.parseLong(entidadRol.getTipoEntidadrol()));
		entidadRol.setTipoEntidadrol(tipoBase.getId().toString());
		
		entidadRoleDao.save(entidadRol);
		
	}

	@Override
	public EntidadRol getEntidadRolByEntidadId(Long entidadId,String tipoEntidadRol) {
		// TODO Auto-generated method stub
		return entidadRoleDao.findByEntidadId(entidadId,tipoEntidadRol);
	}

	@Override
	public EntidadRol findEntidadRolById(Long id) {
		// TODO Auto-generated method stub
		return (EntidadRol) entidadRoleDao.findById(EntidadRol.class, id);
	}

}
