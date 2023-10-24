/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import com.common.domain.Persona;
import com.common.enums.EnableIndicator;
import com.common.facade.DatosMaestrosFacade;
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
@RequestMapping("/"+ PersonaController.NAME)
public class PersonaController {
    public static final String NAME="persona";
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;

    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
        Persona entity = datosMaestrosFacade.findPersonaById(id);
        if (entity==null) {
            return new ResponseEntity(new Persona(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Persona bean) {
        try {
            datosMaestrosFacade.savePersona(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Persona> beanList) {
        try {
            datosMaestrosFacade.savePersona(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Persona> bean) {

        DataTablesOutput dataTablesOutput =  datosMaestrosFacade.findPersonaDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<Persona>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }


    @RequestMapping(value = "/findPersonaByEntidadRolId", method = {RequestMethod.GET})
    public ResponseEntity<?> findPersonaByEntidadRolId(@RequestParam(value = "entidadRolId", required = true) Long entidadRolId) {
        Persona entity = datosMaestrosFacade.findPersonaByEntidadId(entidadRolId);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/findPersonaByTermino", method = {RequestMethod.GET})
    public ResponseEntity<?> findPersonaByTermino(@RequestParam(value = "termino", required = true) String termino) {
        List<Persona> list = datosMaestrosFacade.findByNombreOApellidoONumeroDocumento(termino);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/savePersona", method = {RequestMethod.POST})
    public ResponseEntity<?> saveEntidad(@RequestBody Persona bean) {
        try {
            datosMaestrosFacade.saveEntidadPersona(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 
