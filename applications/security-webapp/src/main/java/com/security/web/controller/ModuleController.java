/**
 * 
 */
package com.security.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView list(
			@RequestParam(value = SecurityConstants.STATUS, required = false) String status,
			@RequestParam(value = SecurityConstants.MESSAGE, required = false) String message){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("list", moduleService.findAllModules());
		modelAndView.setViewName("module");
		modelAndView.addObject(SecurityConstants.STATUS, status);
		modelAndView.addObject(SecurityConstants.MESSAGE, message);
		return modelAndView;
	}
	
	@RequestMapping(value = "/module/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody ModuleView moduleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (moduleView.getId()==null) {
        	moduleService.save((Module) BeanParser.parseObjectToNewClass(moduleView, Module.class, null));
		}else{
			Module module = moduleService.findModuleById(moduleView.getId());
			module = (Module) BeanParser.parseBetweenObjects(moduleView, module, null);
			moduleService.save(module);
		}
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/module/delete"}, method={RequestMethod.POST})
	public ModelAndView delete(@RequestParam(value = "id", required = true) String id){
		Module module = moduleService.findModuleById(Long.valueOf(id));
		moduleService.delete(module);
		ModelAndView modelAndView = list(SecurityConstants.OK, "Your record have been deleted successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return modelAndView;
	}
	
	@RequestMapping(value = "/module/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody ModuleView moduleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Module module = moduleService.findModuleById(moduleView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("module", (ModuleView)BeanParser.parseObjectToNewClass(module, ModuleView.class, null));
        return map;
    }
}
