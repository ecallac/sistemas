/**
 * 
 */
package com.common.controller;

import com.common.domain.Direccion;
import com.common.enums.EnableIndicator;
import com.common.facade.DatosMaestrosFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+ DireccionController.NAME)
public class DireccionController {
    public static final String NAME="direccion";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;
    
    @RequestMapping(value = "/findByEntidadId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByEntidadId(@RequestParam(value = "entidadId", required = true) Long entidadId) {
    	List<Direccion> list = datosMaestrosFacade.findDireccionByEntidadId(entidadId);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findEnabledByEntidadId", method = {RequestMethod.GET})
    public ResponseEntity<?> findEnabledByEntidadId(@RequestParam(value = "entidadId", required = true) Long entidadId) {
        List<Direccion> list = datosMaestrosFacade.findDireccionByEstadoAndEntidadId(EnableIndicator.ENABLED.getCode(),entidadId);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Direccion entity = datosMaestrosFacade.findDireccionById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Direccion bean) {
    	try {
    		datosMaestrosFacade.saveDireccion(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
} 
