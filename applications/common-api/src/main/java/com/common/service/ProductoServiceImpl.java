/**
 * 
 */
package com.common.service;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Producto;
import com.common.repository.ProductoRepository;
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
public class ProductoServiceImpl extends CommonServiceAbstract<Producto> implements ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Override
	public List<Producto> findList() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoRepository.findById(id).get();
	}

	@Override
	public void save(Producto entity) {
		if (entity.getId() != null) {
			Producto entityFromDb = findById(entity.getId());
			entity.setVersion(entityFromDb.getVersion() + 1);
			entityFromDb = (Producto) BeanParser.parseBetweenObjects(entity, entityFromDb);
			entityFromDb.setDateUpdated(new Date());
			productoRepository.save(entityFromDb);
		} else {
			entity.setDateCreated(new Date());
			productoRepository.save(entity);
		}
	}

	@Override
	public Producto findByNombre(String nombre) {
		return productoRepository.findFirstByNombre(nombre);
	}

	@Override
	public DataTablesOutput<Producto> findDataTablesList(DataTablesInput<Producto> dataTablesInput) {
		DataTablesOutput dataTablesOutput = super.getSearchedElements(
				productoRepository.findPageByParameters(dataTablesInput.getSearchValue(), super.getPageRequest(dataTablesInput), dataTablesInput.getObject()),
				productoRepository.count());
		dataTablesOutput.setDraw(dataTablesInput.getDraw());
		return dataTablesOutput;
	}
}
