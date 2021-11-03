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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.domain.Session;
import com.security.domain.User;
import com.security.facade.SecurityFacade;

/**
 * @author efrain.calla
 *
 */
@RestController
@RequestMapping("/"+UserController.NAME)
public class UserController {
    public static final String NAME="user";
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Autowired
    SecurityFacade securityFacade;
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findByUserNameActive", method = {RequestMethod.GET})
    public ResponseEntity<?> findByUserNameActive(@RequestParam(value = "userName", required = true) String userName) {
		User entity = securityFacade.findUserByUserNameActive(userName);
		if (entity==null) {
			return new ResponseEntity(new User(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findByUserName", method = {RequestMethod.GET})
    public ResponseEntity<?> findByUserName(@RequestParam(value = "userName", required = true) String userName) {
		User entity = securityFacade.findUserByUserName(userName);
		if (entity==null) {
			return new ResponseEntity(new User(),HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findList", method = {RequestMethod.GET})
    public ResponseEntity<?> findList() {
		List<User> entityList = securityFacade.findUserList();
		if (CollectionUtils.isEmpty(entityList)) {
			return new ResponseEntity(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entityList,HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/findById", method = {RequestMethod.GET})
    public ResponseEntity<?> findById(@RequestParam(value = "id", required = true) Long id) {
    	User entity = securityFacade.findUserById(id);
		if (entity==null) {
			return new ResponseEntity(new User(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(entity,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseEntity<?> save(@RequestBody User bean) {
    	try {
    		securityFacade.saveUser(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "/savePasswordById", method = {RequestMethod.POST})
    public ResponseEntity<?> savePasswordById(@RequestBody User bean) {
    	try {
    		securityFacade.saveUserPasswordById(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "/addRoleUserAssociation", method = {RequestMethod.POST})
    public ResponseEntity<?> addRoleUserAssociation(@RequestParam(value = "userId", required = true) Long userId,@RequestParam(value = "roleId", required = true) Long roleId) {
    	try {
    		securityFacade.saveRoleInUser(userId, roleId);
    		return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "/removeRoleUserAssociation", method = {RequestMethod.POST})
    public ResponseEntity<?> removeRoleUserAssociation(@RequestParam(value = "userId", required = true) Long userId,@RequestParam(value = "roleId", required = true) Long roleId) {
    	try {
    		securityFacade.deleteRoleInUser(userId, roleId);
    		return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @RequestMapping(value = "/saveSession", method = {RequestMethod.POST})
    public ResponseEntity<?> saveSession(@RequestBody Session bean) {
    	try {
    		securityFacade.saveSession(bean);
    		return new ResponseEntity(bean,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Scheduled(cron="${scheduling.task.cron}")
    public void reviewUser() {
    	logger.info("review");
    }
}
