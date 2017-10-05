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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.utils.BeanParser;
import com.common.utils.CommonUtil;
import com.security.domain.Permission;
import com.security.service.PermissionService;
import com.security.utils.SecurityConstants;
import com.security.web.bean.PermissionView;

/**
 * @author efrain
 *
 */
@Controller
public class PermissionController {
	@Autowired
	PermissionService PermissionService;
	
	@RequestMapping(value={"/permission"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission");
		return modelAndView;
	}
	
  @RequestMapping(value = "/permission/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
      List<PermissionView> list = castPermissionToPermissionViewList(PermissionService.findAllPermissions());
      if (list != null) {
//          map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//          map.put(SecurityConstants.MESSAGE, "Data found");
          map.put("data", list);
      } else {
    	  map.put("data", new ArrayList<PermissionView>());
//          map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
//          map.put(SecurityConstants.MESSAGE, "Data not found");
      }
      return map;
  }
	
	@RequestMapping(value = "/permission/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid PermissionView PermissionView,BindingResult result) {
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
        
        if (PermissionView.getId()==null) {
        	PermissionService.save((Permission) BeanParser.parseObjectToNewClass(PermissionView, Permission.class, null));
		}else{
			Permission Permission = PermissionService.findPermissionById(PermissionView.getId());
			if (Permission!=null) {
				Permission = (Permission) BeanParser.parseBetweenObjects(PermissionView, Permission, null);
				PermissionService.save(Permission);
			}else{
				map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
		        map.put(SecurityConstants.MESSAGE, "Your record couldn't be saved");
		        map.put("validated", true);
		        return map;
			}
		}
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/permission/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody PermissionView PermissionView){
		Map<String, Object> map = new HashMap<String, Object>();
		Permission Permission = PermissionService.findPermissionById(Long.valueOf(PermissionView.getId()));
		PermissionService.delete(Permission);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	@RequestMapping(value = "/permission/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody PermissionView PermissionView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Permission Permission = PermissionService.findPermissionById(PermissionView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (PermissionView)BeanParser.parseObjectToNewClass(Permission, PermissionView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/permission/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody PermissionView PermissionView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Permission Permission = PermissionService.findPermissionById(PermissionView.getId());
        if (Permission!=null) {
			Permission = (Permission) BeanParser.parseBetweenObjects(PermissionView, Permission, null);
			PermissionService.save(Permission);
			map.put(SecurityConstants.STATUS, SecurityConstants.OK);
	        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		}else{
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, "Your status couldn't be updated");
		}
        
        return map;
    }
	
	public List<PermissionView> castPermissionToPermissionViewList(List<Permission> Permissions){
		List<PermissionView> PermissionViews = new ArrayList<PermissionView>();
		for (Permission Permission : Permissions) {
			PermissionViews.add((PermissionView)BeanParser.parseObjectToNewClass(Permission, PermissionView.class, null));
		}
		return PermissionViews;
	}
}
