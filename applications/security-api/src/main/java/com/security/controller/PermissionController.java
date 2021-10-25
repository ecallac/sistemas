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

import com.security.domain.Permission;
import com.security.facade.SecurityFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+PermissionController.NAME)
public class PermissionController {
    public static final String NAME="permission";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    SecurityFacade securityFacade;
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findEnabledListByModuleId", method = {RequestMethod.GET})
    public ResponseEntity<?> findEnabledListByModuleId(@RequestParam(value = "moduleId", required = true) Long moduleId) {
		List<Permission> entityList = securityFacade.findEnabledPermissionsByModuleId(moduleId);
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findEnabledListByModuleName", method = {RequestMethod.GET})
    public ResponseEntity<?> findEnabledListByModuleName(@RequestParam(value = "moduleName", required = true) String moduleName) {
		List<Permission> entityList = securityFacade.findEnabledPermissionsByModuleName(moduleName);
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
		Permission entity = securityFacade.findPermissionById(id);
		if (entity==null) {
			return new ResponseEntity(new Permission(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findEnabledList", method = {RequestMethod.GET})
    public ResponseEntity<?> findEnabledList() {
		List<Permission> entityList = securityFacade.findPermissionEnabledList();
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
		List<Permission> entityList = securityFacade.findPermissionList();
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody Permission bean) {
    	try {
    		securityFacade.savePermission(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}