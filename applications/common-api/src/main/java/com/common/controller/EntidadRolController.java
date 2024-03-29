/**
 * 
 */
package com.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.domain.EntidadRol;
import com.common.facade.DatosMaestrosFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+EntidadRolController.NAME)
public class EntidadRolController {
	public static final String NAME="entidadRol";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody EntidadRol bean) {
    	try {
    		datosMaestrosFacade.saveEntidadRol(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
