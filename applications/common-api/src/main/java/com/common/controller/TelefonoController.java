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
import com.common.facade.CommonFacade;

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
    CommonFacade commonFacade;
    
    @RequestMapping(value = "/findByEntidadId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByEntidadId(@RequestParam(value = "entidadId", required = true) Long entidadId) {
    	List<Telefono> list = commonFacade.findTelefonoByEntidadId(entidadId);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Telefono entity = commonFacade.findTelefonoById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Telefono bean) {
    	try {
    		commonFacade.saveTelefono(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 
