/**
 * 
 */
package com.security.web.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.BeanParser;
import com.security.domain.Module;
import com.security.service.ModuleService;
import com.security.web.bean.ModuleView;

/**
 * @author efrain.calla
 *
 */
@RestController
public class ModuleRestController {
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping(value = "/module/save", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody  Map<String, Object> getSavedPost(@RequestBody ModuleView moduleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        moduleService.save((Module) BeanParser.parseObjectToNewClass(moduleView, Module.class, null));
        map.put("status", "200");
        map.put("message", "Your record have been saved successfully");
        return map;
    }
}
