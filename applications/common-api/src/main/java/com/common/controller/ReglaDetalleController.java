/**
 * 
 */
package com.common.controller;

import com.common.domain.ReglaDetalle;
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
@RequestMapping("/"+ ReglaDetalleController.NAME)
public class ReglaDetalleController {
    public static final String NAME="reglaDetalle";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;
    
    @RequestMapping(value = "/findByCategoria", method = {RequestMethod.GET})
    public ResponseEntity<?> findByCategoria(@RequestParam(value = "categoria", required = true) String categoria) {
    	List<ReglaDetalle> list = datosMaestrosFacade.findReglaByCategoria(categoria);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/findByCodigo", method = {RequestMethod.GET})
    public ResponseEntity<?> findByCodigo(@RequestParam(value = "codigo", required = true) String codigo) {
        List<ReglaDetalle> list = datosMaestrosFacade.findReglaDetalleByCodigo(codigo);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
} 
