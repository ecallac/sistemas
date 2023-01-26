/**
 * 
 */
package com.common.facade;

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
	public void saveArea(Area ubigeo) {
		ubigeo.setParentArea(areaService.findById(ubigeo.getParentArea().getId()));
		areaService.save(ubigeo);
	}
	public List<Area> findAreaByActivo(String activo) {
		return areaService.findByActivo(activo);
	}
	
}
