/**
 * 
 */
package com.common.facade;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Cargo;
import com.common.domain.Marca;
import com.common.domain.Componente;
import com.common.domain.TipoBase;
import com.common.service.MarcaService;
import com.common.service.ComponenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional(readOnly = true)
public class InventarioFacade {

	@Autowired
	DatosMaestrosFacade datosMaestrosFacade;
	@Autowired
	MarcaService marcaService;
	@Autowired
	ComponenteService componenteService;

	public List<Marca> findMarcaList() {
		return marcaService.findList();
	}
	public Marca findMarcaById(Long id){
		return marcaService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveMarca(Marca marca) {
		marcaService.save(marca);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveMarca(List<Marca> marcaList) {
		for (Marca marca :
				marcaList) {
			marcaService.save(marca);
		}
	}
	public List<Marca> findMarcaByStatus(String status) {
		return marcaService.findByStatus(status);
	}
	public Marca findMarcaByNombre(String nombre) {
		return marcaService.findByNombre(nombre);
	}
	public DataTablesOutput findMarcaDataTablesList(DataTablesInput<Marca> dataTablesInput) {
		DataTablesOutput dataTablesOutput = marcaService.findDataTablesList(dataTablesInput);
		return dataTablesOutput;
	}

	public List<Componente> findComponenteList() {
		return componenteService.findList();
	}
	public Componente findComponenteById(Long id){
		return componenteService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveComponente(Componente componente) {
		componenteService.save(componente);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveComponente(List<Componente> componenteList) {
		for (Componente componente :
				componenteList) {
			componenteService.save(componente);
		}
	}
	public List<Componente> findComponenteByStatus(String status) {
		return componenteService.findByStatus(status);
	}
	public Componente findComponenteByNombre(String nombre) {
		return componenteService.findByNombre(nombre);
	}
	public DataTablesOutput findComponenteDataTablesList(DataTablesInput<Componente> dataTablesInput) {
		DataTablesOutput dataTablesOutput = componenteService.findDataTablesList(dataTablesInput);
		return dataTablesOutput;
	}
}
