/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Categoria;
import com.common.domain.Organizacion;
import com.common.enums.EnableIndicator;
import com.common.facade.DatosMaestrosFacade;
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
@RequestMapping("/"+ OrganizacionController.NAME)
public class OrganizacionController {
    public static final String NAME="organizacion";
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;

    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
        Organizacion entity = datosMaestrosFacade.findOrganizacionById(id);
        if (entity==null) {
            return new ResponseEntity(new Organizacion(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/findActivos", method = {RequestMethod.GET})
    public ResponseEntity<?> findByActivos() {
        List<Organizacion> list = datosMaestrosFacade.findOrganizacionByStatus(EnableIndicator.ENABLED.getCode());
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Categoria>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Organizacion bean) {
        try {
            datosMaestrosFacade.saveOrganizacion(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Organizacion> beanList) {
        try {
            datosMaestrosFacade.saveOrganizacion(beanList);
            return new ResponseEntity(beanList,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Organizacion> bean) {

        DataTablesOutput dataTablesOutput =  datosMaestrosFacade.findOrganizacionDataTablesList(bean);
        if (dataTablesOutput==null) {
            return new ResponseEntity(new DataTablesOutput<Organizacion>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput,HttpStatus.OK);
    }

    @RequestMapping(value = "/findOrganizationByEntidadRolId", method = {RequestMethod.GET})
    public ResponseEntity<?> findOrganizationByEntidadRolId(@RequestParam(value = "entidadRolId", required = true) Long entidadRolId) {
        Organizacion entity = datosMaestrosFacade.findOrganizacionByEntidadId(entidadRolId);
        if (entity==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    @RequestMapping(value = "/findOrganizacionByTermino", method = {RequestMethod.GET})
    public ResponseEntity<?> findOrganizacionByTermino(@RequestParam(value = "termino", required = true) String termino) {
        List<Organizacion> list = datosMaestrosFacade.findByRazonSocialONumeroIdentificacion(termino);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/saveOrganizacion", method = {RequestMethod.POST})
    public ResponseEntity<?> saveEntidad(@RequestBody Organizacion bean) {
        try {
            datosMaestrosFacade.saveEntidadOrganizacion(bean);
            return new ResponseEntity(bean,HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 
