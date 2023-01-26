/**
 * 
 */
package com.internal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author efrain
 *
 */
@Controller
public class AreaController {

	
	@RequestMapping(value={"/area"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("area");
		return modelAndView;
	}
	/*
	@RequestMapping(value = "/module/enabledModules", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableModules() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ModuleView> list = castModuleToModuleViewList(moduleIntegration.findEnabledList());
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<ModuleView>());
		}
		return map;
	}
	
  @RequestMapping(value = "/module/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
      List<ModuleView> list = castModuleToModuleViewList(moduleIntegration.findList());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ModuleView moduleView,BindingResult result,Principal principal) {
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
        
		Module module = (Module) BeanParser.parseObjectToNewClass(moduleView, Module.class, null);
		if (module.getId()==null) {
			module.setCreatedBy(principal.getName());
		} else {
			module.setUpdatedBy(principal.getName());
		}
		moduleIntegration.save(module);
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value = "/module/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody ModuleView moduleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Module module = moduleIntegration.findById(moduleView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (ModuleView)BeanParser.parseObjectToNewClass(module, ModuleView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/module/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody ModuleView moduleView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();
		Module module = (Module) BeanParser.parseObjectToNewClass(moduleView, Module.class, null);
		module.setUpdatedBy(principal.getName());
		moduleIntegration.save(module);
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
        
        return map;
    }
	
	public List<ModuleView> castModuleToModuleViewList(List<Module> modules){
		List<ModuleView> moduleViews = new ArrayList<ModuleView>();
		for (Module module : modules) {
			moduleViews.add((ModuleView)BeanParser.parseObjectToNewClass(module, ModuleView.class, null));
		}
		return moduleViews;
	}*/
}
