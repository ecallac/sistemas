/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Entidad;
import com.common.domain.Organizacion;
import com.common.domain.Persona;
import com.common.repository.EntidadRepository;
import com.common.repository.OrganizacionRepository;
import com.common.repository.PersonaRepository;
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
public class PersonaServiceImpl extends CommonServiceAbstract<Persona> implements PersonaService {

	@Autowired
	PersonaRepository personaRepository;
	/* (non-Javadoc)
	 * @see com.common.service.EntidadService#findPersonaByEntidadId(java.lang.Long)
	 */
	@Override
	public Persona findPersonaByEntidadId(Long entidadId) {
		// TODO Auto-generated method stub
		return personaRepository.findPersonaByEntidadId(entidadId);
	}
	@Override
	public List<Persona> findByNombreOApellidoONumeroDocumento(String termino) {
		// TODO Auto-generated method stub
		return personaRepository.findByNombreOApellidoONumeroDocumento(termino);
	}

	@Override
	public DataTablesOutput<Persona> findDataTablesList(DataTablesInput<Persona> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				personaRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				personaRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}

	@Override
	public List<Persona> findList() {
		return null;
	}

	@Override
	public Persona findById(Long id) {
		return personaRepository.findById(id).get();
	}

	@Override
	public void save(Persona entity) {
		if (entity.getId()!=null){
			Persona enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Persona) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			personaRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			personaRepository.save(entity);
		}
	}
}
