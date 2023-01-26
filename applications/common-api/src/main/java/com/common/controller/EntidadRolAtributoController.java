/**
 * 
 */
package com.common.controller;

import com.common.domain.EntidadRol;
import com.common.domain.EntidadRolAtributo;
import com.common.domain.EntidadRolAtributo;
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
@RequestMapping("/"+ EntidadRolAtributoController.NAME)
public class EntidadRolAtributoController {
	public static final String NAME="entidadRolAtributo";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    DatosMaestrosFacade datosMaestrosFacade;

	@RequestMapping(value = "/findByEntidadRolId", method = {RequestMethod.GET})
	public ResponseEntity<?> findByEntidadRolId(@RequestParam(value = "entidadRolId", required = true) Long entidadRolId) {
		List<EntidadRolAtributo> list = datosMaestrosFacade.findEntidadRolAtributoByEntidadRolId(entidadRolId);
		if (CollectionUtils.isEmpty(list)) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(list,HttpStatus.OK);
	}

	@RequestMapping(value = "/findById", method = {RequestMethod.GET})
	public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
		EntidadRolAtributo entity = datosMaestrosFacade.findEntidadRolAtributoById(id);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(entity,HttpStatus.OK);
	}
	
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody EntidadRolAtributo bean) {
    	try {
    		datosMaestrosFacade.saveEntidadRolAtributo(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	
}
