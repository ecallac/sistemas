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
	AreaService areaService;
	@Autowired
	CargoService cargoService;

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
	public List<Area> findAreaByActivo(String activo) {
		return areaService.findByActivo(activo);
	}
	public Area findAreaByNombre(String nombre) {
		return areaService.findByNombre(nombre);
	}
	public DataTablesOutput<Area> findAreaDataTablesList(DataTablesInput<Area> dataTablesInput) {
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
	public List<Cargo> findCargoByActivo(String activo) {
		return cargoService.findByActivo(activo);
	}
	
}
