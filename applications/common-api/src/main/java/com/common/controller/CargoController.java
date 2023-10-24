/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.Utils;
import com.common.domain.Cargo;
import com.common.enums.EnableIndicator;
import com.common.facade.RecurrsosHumanosFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+ CargoController.NAME)
public class CargoController {
    public static final String NAME="cargo";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    RecurrsosHumanosFacade recurrsosHumanosFacade;

    @RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
        List<Cargo> list = recurrsosHumanosFacade.findCargoList();
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Cargo>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findActivos", method = {RequestMethod.GET})
    public ResponseEntity<?> findByActivos() {
        List<Cargo> list = recurrsosHumanosFacade.findCargoByActivo(EnableIndicator.ENABLED.getCode());
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Cargo>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

    @RequestMapping(value = "/findByParentCargoId", method = {RequestMethod.GET})
    public ResponseEntity<?> findByParentCargoId(@RequestParam(value = "parentCargoId", required = true) Long parentCargoId) {
    	List<Cargo> list = recurrsosHumanosFacade.findCargoByParentCargoId(parentCargoId);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(new ArrayList<Cargo>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	Cargo entity = recurrsosHumanosFacade.findCargoById(id);
		if (entity==null) {
			return new ResponseEntity(new Cargo(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Cargo bean) {
    	try {
            recurrsosHumanosFacade.saveCargo(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
            logger.error(e.getMessage(),e);
			e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}

    }
    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Cargo> beanList) {
        try {
            recurrsosHumanosFacade.saveCargo(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value = "/findByNombre", method = {RequestMethod.GET})
    public ResponseEntity<?> findByNombre(@RequestParam(value = "nombre", required = true) String nombre) {
        Cargo entity = recurrsosHumanosFacade.findCargoByNombre(nombre);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Cargo> bean) {

        DataTablesOutput dataTablesOutput =  recurrsosHumanosFacade.findCargoDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<Cargo>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }
} 
