/**
 * 
 */
package com.security.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.utils.BeanParser;
import com.security.domain.Module;
import com.security.service.ModuleService;
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
	public ModelAndView userInfo(
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "message", required = false) String message){
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("list", moduleService.findAllModules());
		modelAndView.setViewName("module");
		modelAndView.addObject("status", status);
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
//	@RequestMapping(value = "/module/save", method = {RequestMethod.GET,RequestMethod.POST})
//    public @ResponseBody  Map<String, Object> getSavedPost(ModuleView moduleView) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        moduleService.save((Module) BeanParser.parseObjectToNewClass(moduleView, Module.class, null));
//        map.put("status", "200");
//        map.put("message", "Your record have been saved successfully");
//        return map;
//    }
}
