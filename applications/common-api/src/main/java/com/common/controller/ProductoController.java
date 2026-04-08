/**
 * 
 */
package com.common.controller;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Producto;
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
@RequestMapping("/" + ProductoController.NAME)
public class ProductoController {
    public static final String NAME = "producto";
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    InventarioFacade inventarioFacade;

    @RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
        List<Producto> list = inventarioFacade.findProductoList();
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Producto>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
        Producto entity = inventarioFacade.findProductoById(id);
        if (entity == null) {
            return new ResponseEntity(new Producto(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Producto bean) {
        try {
            inventarioFacade.saveProducto(bean);
            return new ResponseEntity(bean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/saveList", method = {RequestMethod.POST})
    public ResponseEntity<?> saveList(@RequestBody List<Producto> beanList) {
        try {
            inventarioFacade.saveProducto(beanList);
            return new ResponseEntity(beanList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findByNombre", method = {RequestMethod.GET})
    public ResponseEntity<?> findByNombre(@RequestParam(value = "nombre", required = true) String nombre) {
        Producto entity = inventarioFacade.findProductoByNombre(nombre);
        if (entity == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/findDataTables", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> findDatatables(@RequestBody DataTablesInput<Producto> bean) {
        DataTablesOutput dataTablesOutput = inventarioFacade.findProductoDataTablesList(bean);
        if (dataTablesOutput == null) {
            return new ResponseEntity(new DataTablesOutput<Producto>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(dataTablesOutput, HttpStatus.OK);
    }
}
