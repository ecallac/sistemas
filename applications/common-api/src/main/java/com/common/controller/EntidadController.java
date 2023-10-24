/**
 * 
 */
package com.common.controller;

import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.domain.Area;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.domain.Organizacion;
import com.common.domain.Persona;
import com.common.facade.DatosMaestrosFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+EntidadController.NAME)
public class EntidadController {
	public static final String NAME="entidad";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;




}
