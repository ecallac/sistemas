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
import com.security.domain.Module;
import com.security.service.ModuleService;
import com.security.utils.SecurityConstants;
import com.security.web.bean.ModuleView;

/**
 * @author efrain
 *
 */
@Controller
public class ModuleController {
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value={"/module"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("module");
		return modelAndView;
	}
	
  @RequestMapping(value = "/module/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
      List<ModuleView> list = castModuleToModuleViewList(moduleService.findAllModules());
      if (list != null) {
//          map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//          map.put(SecurityConstants.MESSAGE, "Data found");
          map.put("data", list);
      } else {
    	  map.put("data", new ArrayList<ModuleView>());
//          map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
//          map.put(SecurityConstants.MESSAGE, "Data not found");
      }
      return map;
  }
	
	@RequestMapping(value = "/module/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ModuleView moduleView,BindingResult result) {
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
        
        if (moduleView.getId()==null) {
        	moduleService.save((Module) BeanParser.parseObjectToNewClass(moduleView, Module.class, null));
		}else{
			Module module = moduleService.findModuleById(moduleView.getId());
			if (module!=null) {
				module = (Module) BeanParser.parseBetweenObjects(moduleView, module, null);
				moduleService.save(module);
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
	
	@RequestMapping(value={"/module/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody ModuleView moduleView){
		Map<String, Object> map = new HashMap<String, Object>();
		Module module = moduleService.findModuleById(Long.valueOf(moduleView.getId()));
		moduleService.delete(module);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	@RequestMapping(value = "/module/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody ModuleView moduleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Module module = moduleService.findModuleById(moduleView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (ModuleView)BeanParser.parseObjectToNewClass(module, ModuleView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/module/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody ModuleView moduleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Module module = moduleService.findModuleById(moduleView.getId());
        if (module!=null) {
			module = (Module) BeanParser.parseBetweenObjects(moduleView, module, null);
			moduleService.save(module);
			map.put(SecurityConstants.STATUS, SecurityConstants.OK);
	        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		}else{
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, "Your status couldn't be updated");
		}
        
        return map;
    }
	
	public List<ModuleView> castModuleToModuleViewList(List<Module> modules){
		List<ModuleView> moduleViews = new ArrayList<ModuleView>();
		for (Module module : modules) {
			moduleViews.add((ModuleView)BeanParser.parseObjectToNewClass(module, ModuleView.class, null));
		}
		return moduleViews;
	}
}
