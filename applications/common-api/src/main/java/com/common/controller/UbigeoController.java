/**
 * 
 */
package com.common.controller;

import com.common.domain.Ubigeo;
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
@RequestMapping("/"+ UbigeoController.NAME)
public class UbigeoController {
    public static final String NAME="ubigeo";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;
    
    @RequestMapping(value = "/findByParentUbigeoId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByParentUbigeoId(@RequestParam(value = "parentUbigeoId", required = false) Long parentUbigeoId) {
    	List<Ubigeo> list = datosMaestrosFacade.findUbigeoByParentUbigeoIdAndEstado(parentUbigeoId, EnableIndicator.ENABLED.getCode());
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Ubigeo entity = datosMaestrosFacade.findUbigeoById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Ubigeo bean) {
    	try {
    		datosMaestrosFacade.saveUbigeo(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
} 
