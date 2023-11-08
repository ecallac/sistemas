/**
 * 
 */
package com.common.facade;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.*;
import com.common.service.CategoriaService;
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
	@Autowired
	CategoriaService categoriaService;

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

	public List<Categoria> findCategoriaList() {
		return categoriaService.findList();
	}
	public List<Categoria> findCategoriaByParentCategoriaId(Long parentCategoriaId){
		return categoriaService.findByParentCategoriaId(parentCategoriaId);
	}
	public Categoria findCategoriaById(Long id){
		return categoriaService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveCategoria(Categoria categoria) {
		if (categoria.getCategoriapadre()!=null){
			categoria.setCategoriapadre(categoriaService.findById(categoria.getCategoriapadre().getId()));
		}
		categoriaService.save(categoria);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveCategoria(List<Categoria> categoriaList) {
		for (Categoria categoria : categoriaList) {
			saveCategoria(categoria);
		}

	}
	public List<Categoria> findCategoriaByStatus(String status) {
		return categoriaService.findByStatus(status);
	}
	public Categoria findCategoriaByNombre(String nombre) {
		return categoriaService.findByNombre(nombre);
	}
	public DataTablesOutput findCategoriaDataTablesList(DataTablesInput<Categoria> bean) {
		return categoriaService.findDataTablesList(bean);
	}
}
