/**
 * 
 */
package com.common.controller;

import com.common.domain.ReglaDetalle;
import com.common.domain.Telefono;
import com.common.facade.CommonFacade;
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
    CommonFacade commonFacade;
    
    @RequestMapping(value = "/findByCategoria", method = {RequestMethod.GET})
    public ResponseEntity<?> findByCategoria(@RequestParam(value = "categoria", required = true) String categoria) {
    	List<ReglaDetalle> list = commonFacade.findReglaByCategoria(categoria);
		if (CollectionUtils.isEmpty(list)) {
        	return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
} 
