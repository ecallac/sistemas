/**
 * 
 */
package com.common.controller;

import java.util.List;

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
import com.common.facade.CommonFacade;

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
    CommonFacade commonFacade;
    
    @RequestMapping(value = "/findPersonaByEntidadRolId", method = {RequestMethod.GET})
    public ResponseEntity<?> findPersonaByEntidadRolId(@RequestParam(value = "entidadRolId", required = true) Long entidadRolId) {
    	Persona entity = commonFacade.findPersonaByEntidadId(entidadRolId);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findOrganizationByEntidadRolId", method = {RequestMethod.GET})
    public ResponseEntity<?> findOrganizationByEntidadRolId(@RequestParam(value = "entidadRolId", required = true) Long entidadRolId) {
    	Organizacion entity = commonFacade.findOrganizacionByEntidadId(entidadRolId);
		if (entity==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findPersonaByTermino", method = {RequestMethod.GET})
    public ResponseEntity<?> findPersonaByTermino(@RequestParam(value = "termino", required = true) String termino) {
    	List<Persona> list = commonFacade.findByNombreOApellidoONumeroDocumento(termino);
		if (CollectionUtils.isEmpty(list)) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/savePersona", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Persona bean) {
    	try {
    		commonFacade.savePersona(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
