/**
 * 
 */
package com.common.controller;

import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Componente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET})
    public ResponseEntity<?> findAll() {
        List<TipoBase> list = datosMaestrosFacade.findTipoBaseList();
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
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody TipoBase bean) {
        try {
            datosMaestrosFacade.saveTipoBase(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<TipoBase> beanList) {
        try {
            datosMaestrosFacade.saveTipoBase(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<TipoBase> bean) {

        DataTablesOutput dataTablesOutput =  datosMaestrosFacade.findTipoBaseDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<TipoBase>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }
} 
