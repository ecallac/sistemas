/**
 * 
 */
package com.security.controller;

import java.util.ArrayList;
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

import com.security.domain.Module;
import com.security.facade.SecurityFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+ModuleController.NAME)
public class ModuleController {
    public static final String NAME="module";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    SecurityFacade securityFacade;
    
	@RequestMapping(value = "/findByName", method = {RequestMethod.GET})
    public ResponseEntity<?> findByName(@RequestParam(value = "name", required = true) String name) {
		Module entity = securityFacade.findModuleByName(name);
		if (entity==null) {
        	return new ResponseEntity(new Module(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
		Module entity = securityFacade.findModuleById(id);
		if (entity==null) {
			return new ResponseEntity(new Module(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findEnabledList", method = {RequestMethod.GET})
    public ResponseEntity<?> findEnabledList() {
		List<Module> entityList = securityFacade.findModuleEnabledList();
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
		List<Module> entityList = securityFacade.findModuleList();
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Module bean) {
    	try {
    		securityFacade.saveModule(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}