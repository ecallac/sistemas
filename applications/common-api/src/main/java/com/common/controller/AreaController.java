/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Componente;
import com.common.enums.EnableIndicator;
import com.common.facade.RecurrsosHumanosFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+ AreaController.NAME)
public class AreaController {
    public static final String NAME="area";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    RecurrsosHumanosFacade recurrsosHumanosFacade;

    @RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
        List<Area> list = recurrsosHumanosFacade.findAreaList();
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Area>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findActivos", method = {RequestMethod.GET})
    public ResponseEntity<?> findByActivos() {
        List<Area> list = recurrsosHumanosFacade.findAreaByActivo(EnableIndicator.ENABLED.getCode());
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Area>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findByParentAreaId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByParentAreaId(@RequestParam(value = "parentAreaId", required = true) Long parentAreaId) {
    	List<Area> list = recurrsosHumanosFacade.findAreaByParentAreaId(parentAreaId);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(new ArrayList<Area>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Area entity = recurrsosHumanosFacade.findAreaById(id);
		if (entity==null) {
			return new ResponseEntity(new Area(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Area bean) {
    	try {
            recurrsosHumanosFacade.saveArea(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

} 
