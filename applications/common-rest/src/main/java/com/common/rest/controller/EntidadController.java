/**
 * 
 */
package com.common.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.services.EntidadService;

/**
 * @author efrain
 *
 */
@RestController
public class EntidadController {
	@Autowired
	EntidadService entidadService;
	
	@RequestMapping(value = "/entidad/list", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAll(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList());
		}
		return map;
	}
	
}
