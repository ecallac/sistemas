/**
 * 
 */
package com.common.facade;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.*;
import com.common.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@Service
@Transactional(readOnly = true)
public class RecurrsosHumanosFacade {
	@Autowired
	DatosMaestrosFacade datosMaestrosFacade;
	@Autowired
	AreaService areaService;
	@Autowired
	CargoService cargoService;
	@Autowired
	SucursalService sucursalService;

	public List<Area> findAreaList() {
		return areaService.findList();
	}
	public List<Area> findAreaByParentAreaId(Long parentAreaId){
		return areaService.findByParentAreaId(parentAreaId);
	}
	public Area findAreaById(Long id){
		return areaService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveArea(Area area) {
		if (area.getParentArea()!=null){
			area.setParentArea(areaService.findById(area.getParentArea().getId()));
		}
		areaService.save(area);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveArea(List<Area> areaList) {
		for (Area area : areaList) {
			saveArea(area);
		}

	}
	public List<Area> findAreaByActivo(String activo) {
		return areaService.findByActivo(activo);
	}
	public Area findAreaByNombre(String nombre) {
		return areaService.findByNombre(nombre);
	}
	public DataTablesOutput findAreaDataTablesList(DataTablesInput<Area> dataTablesInput) {
		return areaService.findDataTablesList(dataTablesInput);
	}
	public List<Cargo> findCargoList() {
		return cargoService.findList();
	}
	public List<Cargo> findCargoByParentCargoId(Long parentCargoId){
		return cargoService.findByParentCargoId(parentCargoId);
	}
	public Cargo findCargoById(Long id){
		return cargoService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveCargo(Cargo cargo) {
		if (cargo.getParentCargo()!=null){
			cargo.setParentCargo(cargoService.findById(cargo.getParentCargo().getId()));
		}
		cargoService.save(cargo);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveCargo(List<Cargo> cargoList) {
		for (Cargo cargo : cargoList) {
			saveCargo(cargo);
		}

	}
	public List<Cargo> findCargoByActivo(String activo) {
		return cargoService.findByActivo(activo);
	}
	public Cargo findCargoByNombre(String nombre) {
		return cargoService.findByNombre(nombre);
	}
	public DataTablesOutput findCargoDataTablesList(DataTablesInput<Cargo> bean) {
		return cargoService.findDataTablesList(bean);
	}


	public List<Sucursal> findSucursalList() {
		return sucursalService.findList();
	}
	public Sucursal findSucursalById(Long id){
		return sucursalService.findById(id);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveSucursal(Sucursal sucursal) {
		if (sucursal.getOrganizacion()!=null){
			sucursal.setOrganizacion(datosMaestrosFacade.findOrganizacionById(sucursal.getOrganizacion().getId()));
		}
		sucursalService.save(sucursal);
	}
	@Transactional(readOnly = false,rollbackFor=Exception.class)
	public void saveSucursal(List<Sucursal> sucursalList) {
		for (Sucursal sucursal : sucursalList) {
			saveSucursal(sucursal);
		}

	}
	public List<Sucursal> findSucursalByEstadoAndOrganizacionId(String estado,Long organizacionId) {
		return sucursalService.findByEstadoAndOrganizacionId(estado,organizacionId);
	}
	public Sucursal findSucursalByNombre(String nombre) {
		return sucursalService.findFirstByNombre(nombre);
	}
	public DataTablesOutput findSucursalDataTablesList(DataTablesInput<Sucursal> bean) {
		return sucursalService.findDataTablesList(bean);
	}
}
