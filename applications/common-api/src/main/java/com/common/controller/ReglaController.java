/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Regla;
import com.common.domain.TipoBase;
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
@RequestMapping("/"+ ReglaController.NAME)
public class ReglaController {
    public static final String NAME="regla";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;

    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Regla entity = datosMaestrosFacade.findReglaById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Regla bean) {
        try {
            datosMaestrosFacade.saveRegla(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Regla> beanList) {
        try {
            datosMaestrosFacade.saveRegla(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Regla> bean) {

        DataTablesOutput dataTablesOutput =  datosMaestrosFacade.findReglaDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<Regla>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }

    @RequestMapping(value = "/findByCodigo", method = {RequestMethod.GET})
    public ResponseEntity<?> findByCodigo(@RequestParam(value = "codigo", required = true) String codigo) {
        Regla entity = datosMaestrosFacade.findReglaByCodigo(codigo);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
} 
