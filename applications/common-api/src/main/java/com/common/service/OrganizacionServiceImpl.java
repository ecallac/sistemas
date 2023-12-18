/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
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
public class OrganizacionServiceImpl extends CommonServiceAbstract<Organizacion> implements OrganizacionService {

	@Autowired
	OrganizacionRepository organizacionRepository;
	/* (non-Javadoc)
	 * @see com.common.service.EntidadService#findPersonaByEntidadId(java.lang.Long)
	 */
	@Override
	public Organizacion findOrganizacionByEntidadId(Long entidadId) {
		// TODO Auto-generated method stub
		return organizacionRepository.findOrganizacionByEntidadId(entidadId);
	}

	@Override
	public List<Organizacion> findByRazonSocialONumeroIdentificacion(String termino) {
		return organizacionRepository.findByRazonSocialONumeroIdentificacion(termino);
	}

	@Override
	public DataTablesOutput<Organizacion> findDataTablesList(DataTablesInput<Organizacion> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				organizacionRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				organizacionRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}

	@Override
	public List<Organizacion> findByStatus(String status) {
		return organizacionRepository.findByStatus(status);
	}

	@Override
	public List<Organizacion> findList() {
		return null;
	}

	@Override
	public Organizacion findById(Long id) {
		return organizacionRepository.findById(id).get();
	}

	@Override
	public void save(Organizacion entity) {
		if (entity.getId()!=null){
			Organizacion enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Organizacion) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			organizacionRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			organizacionRepository.save(entity);
		}
	}
}
