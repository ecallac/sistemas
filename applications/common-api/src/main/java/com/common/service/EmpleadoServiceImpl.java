/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Empleado;
import com.common.repository.EmpleadoRepository;
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
public class EmpleadoServiceImpl extends CommonServiceAbstract<Empleado> implements EmpleadoService {

	@Autowired
	EmpleadoRepository empleadoRepository;
	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findList()
	 */
	@Override
	public List<Empleado> findList() {
		// TODO Auto-generated method stub
		return empleadoRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#findById(java.lang.Long)
	 */
	@Override
	public Empleado findById(Long id) {
		// TODO Auto-generated method stub
		return empleadoRepository.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.common.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(Empleado entity) {
		// TODO Auto-generated method stub
		if (entity.getId()!=null){
			Empleado enrityFromDb = findById(entity.getId());
			entity.setVersion(enrityFromDb.getVersion()+1);
			enrityFromDb = (Empleado) BeanParser.parseBetweenObjects(entity, enrityFromDb);
			enrityFromDb.setDateUpdated(new Date());
			empleadoRepository.save(enrityFromDb);
		}else{
			entity.setDateCreated(new Date());
			empleadoRepository.save(entity);
		}
	}

	/**
	 * @param dataTablesInput
	 * @return
	 */
	@Override
	public DataTablesOutput<Empleado> findDataTablesList(DataTablesInput<Empleado> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				empleadoRepository.findPageByParameters(dataTablesInput.getSearchValue(),super.getPageRequest(dataTablesInput),dataTablesInput.getObject()),
				empleadoRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}


	@Override
	public List<Empleado> findByReportaId(Long reportaId) {
		return empleadoRepository.findByReportaId(reportaId);
	}

	@Override
	public List<Empleado> findBySupervisorausenciasId(Long supervisorausenciasId) {
		return empleadoRepository.findBySupervisorausenciasId(supervisorausenciasId);
	}

	@Override
	public Empleado findFirstByCodigo(String codigo) {
		return empleadoRepository.findFirstByCodigo(codigo);
	}

	@Override
	public List<Empleado> findByEstado(String activo) {
		return empleadoRepository.findByEstado(activo);
	}

}
