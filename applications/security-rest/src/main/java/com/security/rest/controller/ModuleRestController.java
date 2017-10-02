/**
 * 
 */
package com.security.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.client.bean.ModuleBean;
import com.security.client.canonical.UserCanonicalResponse;
import com.security.domain.Module;
import com.security.service.ModuleService;

/**
 * @author efrain.calla
 *
 */
@RestController
public class ModuleRestController {
	@Autowired
	ModuleService moduleService;
	
//	@RequestMapping(value = "/modules", //
//            method = RequestMethod.GET, //
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    public UserCanonicalResponse getPermissions() {
//		List<Module> modules = moduleService.findAllModules();
//        return convertModulesToUserCanonicalResponse(modules);
//    }
	
//	public UserCanonicalResponse convertModulesToUserCanonicalResponse(List<Module> modules){
//		UserCanonicalResponse userCanonicalResponse = new UserCanonicalResponse();
//		List<ModuleBean> moduleBeans = new ArrayList<>();
//		for (Module module : modules) {
//			ModuleBean moduleBean = new ModuleBean();
//			moduleBean.setName(module.getName());
//			moduleBean.setDescription(module.getDescription());
//			moduleBeans.add(moduleBean);
//		}
//		userCanonicalResponse.setResponseObject(moduleBeans);
//		userCanonicalResponse.setStatus(UserCanonicalResponse.STATUS_OK);
//		return userCanonicalResponse;
//	}
}
