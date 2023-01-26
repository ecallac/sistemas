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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.domain.TipoBase;
import com.common.facade.DatosMaestrosFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+TipoBaseController.NAME)
public class TipoBaseController {
    public static final String NAME="tipobase";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;
    
    @RequestMapping(value = "/findByCategoriaActivos", method = {RequestMethod.GET})
    public ResponseEntity<?> findByCategoriaActivos(@RequestParam(value = "categoria", required = true) String categoria) {
    	List<TipoBase> list = datosMaestrosFacade.findTipoBaseByCategoriaActivos(categoria);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findByCodigo", method = {RequestMethod.GET})
    public ResponseEntity<?> findByCodigo(@RequestParam(value = "codigo", required = true) String codigo) {
    	TipoBase entity = datosMaestrosFacade.findTipoBaseByCodigo(codigo);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	TipoBase entity = datosMaestrosFacade.findTipoBaseById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
} 
