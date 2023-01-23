/**
 * 
 */
package com.common.facade;

import java.util.List;

import com.common.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.service.EntidadRolService;
import com.common.service.EntidadService;
import com.common.service.ReglaDetalleService;
import com.common.service.TelefonoService;
import com.common.service.TipoBaseService;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional(readOnly = true)
public class CommonFacade {
	@Autowired
	TipoBaseService tipoBaseService;
	
	@Autowired
	EntidadService entidadService;
	
	@Autowired
	EntidadRolService entidadRolService;
	
	@Autowired
	ReglaDetalleService reglaDetalleService;
	
	@Autowired
	TelefonoService telefonoService;

	
	public TipoBase findTipoBaseById(Long id) {
		return tipoBaseService.findById(id);
	}
	public TipoBase findTipoBaseByCodigo(String codigo) {
		return tipoBaseService.findByCodigo(codigo);
	}
	public List<TipoBase> findTipoBaseByCategoriaActivos(String categoria){
		return tipoBaseService.findByCategoriaActivos(categoria);
	}
	
	public Persona findPersonaByEntidadId(Long entidadRolId) {
		EntidadRol entidadRol = entidadRolService.findById(entidadRolId);
		return entidadService.findPersonaByEntidadId(entidadRol.getEntidad().getId());
	}
	
	public Organizacion findOrganizacionByEntidadId(Long entidadRolId) {
		EntidadRol entidadRol = entidadRolService.findById(entidadRolId);
		return entidadService.findOrganizacionByEntidadId(entidadRol.getEntidad().getId());
	}
	
	public List<Persona> findByNombreOApellidoONumeroDocumento(String termino){
		return entidadService.findByNombreOApellidoONumeroDocumento(termino);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void savePersona(Persona persona) {
		entidadService.savePersona(persona);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveEntidadRol(EntidadRol entidadRol) {
		entidadRol.setEntidad(entidadService.findById(entidadRol.getEntidad().getId()));
		entidadRolService.save(entidadRol);
	}
	
	public List<Telefono> findTelefonoByEntidadId(Long entidadId){
		return telefonoService.findByEntidadId(entidadId);
	}
	public Telefono findTelefonoById(Long id) {
		return telefonoService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveTelefono(Telefono telefono) {
		telefono.setEntidad(entidadService.findById(telefono.getEntidad().getId()));
		telefonoService.save(telefono);
	}

	public  List<ReglaDetalle> findReglaByCategoria(String categoria) {
		return reglaDetalleService.findByReglaCategoria(categoria);
	}

	public ReglaDetalle findReglaDetalleById(Long id) {
		return reglaDetalleService.findById(id);
	}

	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveReglaDetalle(ReglaDetalle entidad) {
		reglaDetalleService.save(entidad);
	}

}
