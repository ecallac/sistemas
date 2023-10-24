/**
 * 
 */
package com.common.service;

import java.util.Date;
import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.repository.OrganizacionRepository;
import com.common.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;
import com.common.repository.EntidadRepository;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional
public class EntidadServiceImpl implements EntidadService {
	
	@Autowired
	EntidadRepository entidadRepository;
	/* (non-Javadoc)
	 * @see com.common.service.EntidadService#findPersonaByEntidadId(java.lang.Long)
	 */
	@Override
	public void savePersona(Persona persona) {
		if (persona.getId()!=null){
			
//			Persona enrityFromDb = findById(persona.getId());
//			persona.setVersion(enrityFromDb.getVersion()+1);
//            enrityFromDb = (Persona) BeanParser.parseBetweenObjects(persona, enrityFromDb);
//            enrityFromDb.setDateUpdated(new Date());
//            entidadRepository.save(enrityFromDb);
        }else{
        	persona.setDateCreated(new Date());
        	Entidad entidad = persona.getEntidad();
        	entidad.setPersona(persona);
        	entidad.setDateCreated(new Date());
        	entidadRepository.save(entidad);
        }
	}

	@Override
	public void saveOrganizacion(Organizacion organizacion) {
		if (organizacion.getId()!=null){

//			Organizacion enrityFromDb = findById(organizacion.getId());
//			organizacion.setVersion(enrityFromDb.getVersion()+1);
//            enrityFromDb = (Organizacion) BeanParser.parseBetweenObjects(organizacion, enrityFromDb);
//            enrityFromDb.setDateUpdated(new Date());
//            entidadRepository.save(enrityFromDb);
		}else{
			organizacion.setDateCreated(new Date());
			Entidad entidad = organizacion.getEntidad();
			entidad.setOrganizacion(organizacion);
			entidad.setDateCreated(new Date());
			entidadRepository.save(entidad);
		}
	}
	@Override
	public List<Entidad> findList() {
		// TODO Auto-generated method stub
		return entidadRepository.findAll();
	}
	@Override
	public Entidad findById(Long id) {
		// TODO Auto-generated method stub
		return entidadRepository.findById(id).get();
	}
	@Override
	public void save(Entidad entity) {
		// TODO Auto-generated method stub
		
	}

}
