/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.Utils;
import com.common.domain.Area;
import com.common.domain.Marca;
import com.common.enums.EnableIndicator;
import com.common.facade.InventarioFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+ MarcaController.NAME)
public class MarcaController {
    public static final String NAME="marca";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    InventarioFacade inventarioFacade;

    @RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
        List<Marca> list = inventarioFacade.findMarcaList();
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Marca>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findActivos", method = {RequestMethod.GET})
    public ResponseEntity<?> findActivos() {
        List<Marca> list = inventarioFacade.findMarcaByStatus(EnableIndicator.ENABLED.getCode());
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Marca>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Marca entity = inventarioFacade.findMarcaById(id);
		if (entity==null) {
			return new ResponseEntity(new Marca(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Marca bean) {
    	try {
            inventarioFacade.saveMarca(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Marca> beanList) {
        try {
            inventarioFacade.saveMarca(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/findByNombre", method = {RequestMethod.GET})
    public ResponseEntity<?> findByNombre(@RequestParam(value = "nombre", required = true) String nombre) {
        Marca entity = inventarioFacade.findMarcaByNombre(nombre);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }

    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Marca> bean) {

        DataTablesOutput dataTablesOutput =  inventarioFacade.findMarcaDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<Marca>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }
} 
