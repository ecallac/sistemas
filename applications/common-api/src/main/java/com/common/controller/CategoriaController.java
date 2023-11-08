/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Categoria;
import com.common.enums.EnableIndicator;
import com.common.facade.InventarioFacade;
import com.common.facade.RecurrsosHumanosFacade;
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
@RequestMapping("/"+ CategoriaController.NAME)
public class CategoriaController {
    public static final String NAME="categoria";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    InventarioFacade inventarioFacade;

    @RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
        List<Categoria> list = inventarioFacade.findCategoriaList();
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Categoria>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findActivos", method = {RequestMethod.GET})
    public ResponseEntity<?> findByActivos() {
        List<Categoria> list = inventarioFacade.findCategoriaByStatus(EnableIndicator.ENABLED.getCode());
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Categoria>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findByParentCategoriaId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByParentCategoriaId(@RequestParam(value = "parentCategoriaId", required = true) Long parentCategoriaId) {
    	List<Categoria> list = inventarioFacade.findCategoriaByParentCategoriaId(parentCategoriaId);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(new ArrayList<Categoria>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Categoria entity = inventarioFacade.findCategoriaById(id);
		if (entity==null) {
			return new ResponseEntity(new Categoria(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Categoria bean) {
    	try {
            inventarioFacade.saveCategoria(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
			e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }
    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Categoria> beanList) {
        try {
            inventarioFacade.saveCategoria(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value = "/findByNombre", method = {RequestMethod.GET})
    public ResponseEntity<?> findByNombre(@RequestParam(value = "nombre", required = true) String nombre) {
        Categoria entity = inventarioFacade.findCategoriaByNombre(nombre);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Categoria> bean) {

        DataTablesOutput dataTablesOutput =  inventarioFacade.findCategoriaDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<Categoria>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }
} 
