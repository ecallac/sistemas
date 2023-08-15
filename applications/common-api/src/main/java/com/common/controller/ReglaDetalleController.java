/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Regla;
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

    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
        ReglaDetalle entity = datosMaestrosFacade.findReglaDetalleById(id);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody ReglaDetalle bean) {
        try {
            datosMaestrosFacade.saveReglaDetalle(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<ReglaDetalle> beanList) {
        try {
            datosMaestrosFacade.saveReglaDetalle(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<ReglaDetalle> bean) {

        DataTablesOutput dataTablesOutput =  datosMaestrosFacade.findReglaDetalleDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<ReglaDetalle>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }
} 
