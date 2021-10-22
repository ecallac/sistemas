/**
 * 
 */
package com.security.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.BeanParser;
import com.security.Role;
import com.security.web.bean.RoleView;
import com.security.web.service.integration.RoleIntegration;
import com.security.web.utils.SecurityConstants;
import com.security.web.utils.SecurityUtil;

/**
 * @author efrain
 *
 */
@Controller
public class RoleController {
	@Autowired
	RoleIntegration roleIntegration;
	
	@RequestMapping(value={"/role"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role");
		return modelAndView;
	}
	
	@RequestMapping(value = "/role/enabledRoles", method = {RequestMethod.GET,RequestMethod.POST})
	  public @ResponseBody Map<String, Object> initializeEnabledRoles() {
	      Map<String, Object> map = new HashMap<String, Object>();
	      List<RoleView> list = castRoleToRoleViewList(roleIntegration.findEnabledList());
	      if (list != null) {
	          map.put("data", list);
	      } else {
	    	  map.put("data", new ArrayList<RoleView>());
	      }
	      return map;
	  }
	
  @RequestMapping(value = "/role/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
      List<RoleView> list = castRoleToRoleViewList(roleIntegration.findList());
      if (list != null) {
//          map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//          map.put(SecurityConstants.MESSAGE, "Data found");
          map.put("data", list);
      } else {
    	  map.put("data", new ArrayList<RoleView>());
//          map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
//          map.put(SecurityConstants.MESSAGE, "Data not found");
      }
      return map;
  }
	
	@RequestMapping(value = "/role/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid RoleView RoleView,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	      }
        
		Role role = (Role) BeanParser.parseObjectToNewClass(RoleView, Role.class, null);
		if (role.getId()==null) {
			role.setCreatedBy(principal.getName());
		} else {
			role.setUpdatedBy(principal.getName());
		}
		roleIntegration.save(role);
		
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
//	@RequestMapping(value={"/role/delete"}, method={RequestMethod.POST})
//	public @ResponseBody Map<String, Object> delete(@RequestBody RoleView RoleView){
//		Map<String, Object> map = new HashMap<String, Object>();
//		Role Role = securityServiceIntegration.findRoleById(Long.valueOf(RoleView.getId()));
//		securityServiceIntegration.delete(Role);
//        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        return map;
//	}
	
	@RequestMapping(value = "/role/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody RoleView RoleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Role Role = roleIntegration.findById(RoleView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (RoleView)BeanParser.parseObjectToNewClass(Role, RoleView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/role/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody RoleView RoleView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();
		Role role = (Role) BeanParser.parseObjectToNewClass(RoleView, Role.class, null);
		role.setUpdatedBy(principal.getName());
		roleIntegration.save(role);
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        
        return map;
    }
	
	public List<RoleView> castRoleToRoleViewList(List<Role> Roles){
		List<RoleView> RoleViews = new ArrayList<RoleView>();
		for (Role Role : Roles) {
			RoleViews.add((RoleView)BeanParser.parseObjectToNewClass(Role, RoleView.class, null));
		}
		return RoleViews;
	}
}
