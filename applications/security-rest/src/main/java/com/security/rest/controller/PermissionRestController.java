package com.security.rest.controller;
///**
// * 
// */
//package com.security.web.rest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.security.client.bean.PermissionBean;
//import com.security.client.bean.UserCanonicalResponse;
//import com.security.domain.Module;
//import com.security.domain.Permission;
//import com.security.service.ModuleService;
//import com.security.service.PermissionService;
//
///**
// * @author efrain.calla
// *
// */
//@RestController
//public class PermissionRestController {
//	@Autowired
//	ModuleService moduleService;
//	@Autowired
//	PermissionService permissionService;
//	
//	@RequestMapping(value = "/permission/{moduleName}", //
//            method = RequestMethod.GET, //
//            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    public UserCanonicalResponse getPermissions(@PathVariable("moduleName") String moduleName) {
//		Module module = moduleService.findByName(moduleName);
//		List<Permission> permissions = permissionService.findPermissionsByModuleId(module.getId());
//        return convertPermissionToUserCanonicalResponse(user, roles);
//    }
//	public UserCanonicalResponse convertPermissionToUserCanonicalResponse(List<Permission> permissions){
//		UserCanonicalResponse userCanonicalResponse = new UserCanonicalResponse();
//		List<PermissionBean> permissionBeans = new ArrayList<>();
//		for (Permission permission : permissions) {
//			PermissionBean permissionBean = new PermissionBean();
//			permissionBean.se
//		}
//		userCanonicalResponse.setResponseObject(bean);
//		userCanonicalResponse.setStatus(UserCanonicalResponse.STATUS_OK);
//		return userCanonicalResponse;
//	}
//}
