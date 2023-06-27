/**
 * 
 */
package com.common.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.domain.Telefono;
import com.common.facade.DatosMaestrosFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+TelefonoController.NAME)
public class TelefonoController {
    public static final String NAME="telefono";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;
    
    @RequestMapping(value = "/findByEntidadId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByEntidadId(@RequestParam(value = "entidadId", required = true) Long entidadId) {
    	List<Telefono> list = datosMaestrosFacade.findTelefonoByEntidadId(entidadId);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Telefono entity = datosMaestrosFacade.findTelefonoById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Telefono bean) {
    	try {
    		datosMaestrosFacade.saveTelefono(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
} 
