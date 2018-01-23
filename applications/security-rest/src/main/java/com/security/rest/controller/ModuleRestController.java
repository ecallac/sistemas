/**
 * 
 */
package com.security.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.common.utils.BeanParser;
import com.security.client.bean.ModuleBean;
import com.security.client.canonical.ModuleCanonicalRequest;
import com.security.client.canonical.ModuleCanonicalResponse;
import com.security.client.utils.XmlUtils;
import com.security.domain.Module;
import com.security.service.ModuleService;
import com.security.service.PermissionService;

/**
 * @author efrain.calla
 *
 */
@RestController
public class ModuleRestController {
	@Autowired
	ModuleService moduleService;
	
	@Autowired
	PermissionService permissionService;
	
	@RequestMapping(value = "/getRolesOfPermissionByModule", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public String getRolesOfPermissionByModule(@RequestBody ModuleCanonicalRequest request) {
		System.out.println(":::getRolesOfPermissionByModule");
		Module module = moduleService.findByName(request.getModuleName());
		if (module==null) {
        	System.out.println(":::error");
        	ModuleCanonicalResponse response = new ModuleCanonicalResponse();
        	response.setStatus(ModuleCanonicalResponse.STATUS_ERROR);
        	response.setMessage("Module Name was not found in database.");
        	return XmlUtils.toXml(ModuleCanonicalResponse.class,response);
		}
		Map<String, String> roles= permissionService.getRolesOfPermissionByModuleId(module.getId());
		
		
        return XmlUtils.toXml(ModuleCanonicalResponse.class,createModuleCanonicalResponse(module, roles));
    }

	private ModuleCanonicalResponse createModuleCanonicalResponse(Module module, Map<String, String> roles) {
		ModuleCanonicalResponse response = new ModuleCanonicalResponse();
		ModuleBean moduleBean = (ModuleBean) BeanParser.parseObjectToNewClass(module, ModuleBean.class, null);;
		moduleBean.setRolesByPermission(roles);
		response.setModuleBean(moduleBean);
		response.setStatus(ModuleCanonicalResponse.STATUS_OK);
		return response;
	}

}
