/**
 * 
 */
package com.common.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.dao.EntidadDao;
import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;

/**
 * @author efrain.calla
 *
 */
@Transactional
@Service("entidadService")
public class EntidadServiceImpl implements EntidadService {

	@Autowired
	EntidadDao entidadDao;
	
	@Override
	public void save(Organizacion organizacion) {
		// TODO Auto-generated method stub
		entidadDao.save(organizacion);
	}

	@Override
	public void save(Persona persona) {
		// TODO Auto-generated method stub
		entidadDao.save(persona);
	}

	@Override
	public Entidad findById(Long id) {
		// TODO Auto-generated method stub
		return (Entidad) entidadDao.findById(Entidad.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organizacion> findAllOrganizacion() {
		// TODO Auto-generated method stub
		return (List<Organizacion>) entidadDao.findAll(Organizacion.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> findAllPersona() {
		// TODO Auto-generated method stub
		return (List<Persona>) entidadDao.findAll(Persona.class);
	}

	@Override
	public List<Organizacion> findOrganizacionPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return entidadDao.findOrganizacionPorNombre(nombre);
	}

	@Override
	public List<Persona> findPersonaPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return entidadDao.findPersonaPorNombre(nombre);
	}

}
