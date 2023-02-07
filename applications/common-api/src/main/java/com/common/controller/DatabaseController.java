/**
 * 
 */
package com.common.controller;

import com.common.Column;
import com.common.Table;
import com.common.domain.Area;
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
@RequestMapping("/"+ DatabaseController.NAME)
public class DatabaseController {
    public static final String NAME="database";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;

    @RequestMapping(value = "/findTableList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList(@RequestParam(value = "catalog", required = false) String catalog) throws Exception{
        List<Table> list = datosMaestrosFacade.findDatabaseTables(catalog);
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity(new ArrayList<Area>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @RequestMapping(value = "/findColumnListByTable", method = {RequestMethod.GET})
    public ResponseEntity<?> findByParentAreaId(@RequestParam(value = "table", required = true) String table) throws Exception{
    	List<Column> list = datosMaestrosFacade.findDatabaseColumnsByTable(table);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(new ArrayList<Area>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }

} 
