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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import com.common.TipoBase;
import com.security.Module;
import com.security.Permission;
import com.security.web.bean.ModuleView;
import com.security.web.bean.PermissionView;
import com.security.web.service.integration.PermissionIntegration;
import com.security.web.service.integration.TipoBaseIntegration;
import com.security.web.utils.SecurityConstants;
import com.security.web.utils.SecurityUtil;

/**
 * @author efrain
 *
 */
@Controller
public class PermissionController {
	
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	
	@Autowired
	PermissionIntegration permissionIntegration;
	
	@RequestMapping(value={"/permission"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission");
		return modelAndView;
	}
	
	@RequestMapping(value = "/permission/permissionType", method = {RequestMethod.GET,RequestMethod.POST})
	  public @ResponseBody Map<String, Object> initializePermissionType() {
	      Map<String, Object> map = new HashMap<String, Object>();
	      List<TipoBase> list = tipoBaseIntegration.findByCategoriaActivos(SecurityConstants.TIPOBASE_CATEGORIA_TYPE_PERMISSION);
	      if (list != null) {
	          map.put("data", list);
	      } else {
	    	  map.put("data", new ArrayList<TipoBase>());
	      }
	      return map;
	  }
	
	@RequestMapping(value = "/permission/enabledPermissionsByModule", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> getPeremissionsByModule(@RequestBody PermissionView permissionView) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<PermissionView> list = castPermissionToPermissionViewList(permissionIntegration.findEnabledListByModuleId(Long.valueOf(permissionView.getModuleId())));
        if (list != null) {
          map.put("data", list);
      } else {
    	  map.put("data", new ArrayList<PermissionView>());
      }
      return map;
    }
	
  @RequestMapping(value = "/permission/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
      List<PermissionView> list = castPermissionToPermissionViewList(permissionIntegration.findList());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid PermissionView permissionView,BindingResult result,Principal principal) {
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
		
		Permission permission = (Permission) BeanParser.parseObjectToNewClass(permissionView, Permission.class, null);
		if (StringUtils.isNotBlank(permissionView.getModuleId())) {
			permission.setModule((Module)BeanParser.parseObjectToNewClass(permissionView.getModule(), Module.class, null));
		}
		if (StringUtils.isNotBlank(permissionView.getParentPermissionId())) {
			permission.setParentPermission((Permission)BeanParser.parseObjectToNewClass(permissionView.getParentPermission(), Permission.class, null));
		}
		if (permissionView.getId()==null) {
			permission.setCreatedBy(principal.getName());
		} else {
			permission.setUpdatedBy(principal.getName());
		}
		permissionIntegration.save(permission);
		
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
//	@RequestMapping(value={"/permission/delete"}, method={RequestMethod.POST})
//	public @ResponseBody Map<String, Object> delete(@RequestBody PermissionView permissionView){
//		Map<String, Object> map = new HashMap<String, Object>();
//		try {
//			Permission Permission = securityServiceIntegration.findPermissionById(Long.valueOf(permissionView.getId()));
//			securityServiceIntegration.delete(Permission);
//	        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//	        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		} catch (DataIntegrityViolationException e) {
//			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
//			map.put(SecurityConstants.MESSAGE, "System couln't do the action, Review data dependences.");
//		} catch (Exception e) {
//			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
//			map.put(SecurityConstants.MESSAGE, e.getMessage());
//		}
//		
//        return map;
//	}
	
	@RequestMapping(value = "/permission/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody PermissionView permissionView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Permission permission = permissionIntegration.findById(permissionView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        
        PermissionView permissionViewStored = (PermissionView)BeanParser.parseObjectToNewClass(permission, PermissionView.class, null);
        permissionViewStored.setModule((ModuleView)BeanParser.parseObjectToNewClass(permission.getModule(), ModuleView.class, null));
        permissionViewStored.setParentPermission((PermissionView)BeanParser.parseObjectToNewClass(permission.getParentPermission(), PermissionView.class, null));
		
        map.put("viewBean", permissionViewStored);
        return map;
    }
	
	@RequestMapping(value = "/permission/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody PermissionView permissionView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();
    	Permission permission = (Permission) BeanParser.parseObjectToNewClass(permissionView, Permission.class, null);
    	permission.setUpdatedBy(principal.getName());
    	permissionIntegration.save(permission);
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
        return map;
    }
	
	public List<PermissionView> castPermissionToPermissionViewList(List<Permission> permissions){
		List<PermissionView> PermissionViews = new ArrayList<PermissionView>();
		for (Permission permission : permissions) {
			PermissionView permissionView = (PermissionView)BeanParser.parseObjectToNewClass(permission, PermissionView.class, null);
			permissionView.setModule((ModuleView)BeanParser.parseObjectToNewClass(permission.getModule(), ModuleView.class, null));
			permissionView.setParentPermission((PermissionView)BeanParser.parseObjectToNewClass(permission.getParentPermission(), PermissionView.class, null));
			PermissionViews.add(permissionView);
		}
		return PermissionViews;
	}
}
