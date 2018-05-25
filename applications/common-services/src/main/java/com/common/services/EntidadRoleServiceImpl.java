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
	public void save(EntidadRol entidadRol) {
		Entidad entidad = entidadService.findById(entidadRol.getEntidad().getId());
		entidadRol.setEntidad(entidad);
		
		TipoBase tipoBase = tipoBaseService.findTipoBaseById(Long.parseLong(entidadRol.getTipoEntidadrol()));
		entidadRol.setTipoEntidadrol(tipoBase.getId().toString());
		
		entidadRoleDao.save(entidadRol);
		
	}

}
