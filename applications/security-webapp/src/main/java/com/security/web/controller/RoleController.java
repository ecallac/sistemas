/**
 * 
 */
package com.security.web.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.utils.BeanParser;
import com.common.utils.CommonConstants;
import com.common.utils.CommonUtil;
import com.security.domain.Role;
import com.security.service.RoleService;
import com.security.utils.SecurityConstants;
import com.security.web.bean.ModuleView;
import com.security.web.bean.RoleView;

/**
 * @author efrain
 *
 */
@Controller
public class RoleController {
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value={"/role"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("role");
		return modelAndView;
	}
	
	@RequestMapping(value = "/role/enabledRoles", method = {RequestMethod.GET,RequestMethod.POST})
	  public @ResponseBody Map<String, Object> initializeEnabledRoles() {
	      Map<String, Object> map = new HashMap<String, Object>();
	      List<RoleView> list = castRoleToRoleViewList(roleService.findByEnabled(CommonConstants.YES));
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
      List<RoleView> list = castRoleToRoleViewList(roleService.findAllRoles());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid RoleView RoleView,BindingResult result) {
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
        
		roleService.save((Role) BeanParser.parseObjectToNewClass(RoleView, Role.class, null));
		
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/role/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody RoleView RoleView){
		Map<String, Object> map = new HashMap<String, Object>();
		Role Role = roleService.findRoleById(Long.valueOf(RoleView.getId()));
		roleService.delete(Role);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	@RequestMapping(value = "/role/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody RoleView RoleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Role Role = roleService.findRoleById(RoleView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (RoleView)BeanParser.parseObjectToNewClass(Role, RoleView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/role/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody RoleView RoleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Role Role = roleService.findRoleById(RoleView.getId());
        if (Role!=null) {
			Role = (Role) BeanParser.parseBetweenObjects(RoleView, Role, null);
			roleService.save(Role);
			map.put(SecurityConstants.STATUS, SecurityConstants.OK);
	        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		}else{
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, "Your status couldn't be updated");
		}
        
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
