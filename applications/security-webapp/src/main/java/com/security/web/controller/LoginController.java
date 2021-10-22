/**
 * 
 */
package com.security.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.security.Module;
import com.security.Permission;
import com.security.web.service.integration.ModuleIntegration;
import com.security.web.service.integration.PermissionIntegration;
import com.security.web.utils.SecurityConstants;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 14:03:23
 */
@Controller
public class LoginController {
	
	@Autowired
	PermissionIntegration permissionIntegration;
	
	@Autowired
	ModuleIntegration moduleIntegration;
	
	@RequestMapping(value="/login", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(@RequestParam(value = "error",required = false) String error,@RequestParam(value = "logout",	required = false) String logout){
		ModelAndView modelAndView = new ModelAndView();
		if (error != null) {
			modelAndView.addObject("error", "Username or password is incorrect!");
		}
		
//		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		
		
		if (logout != null) {
			modelAndView.addObject("message", "Logged out from page successfully.");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value={"/403"}, method=RequestMethod.GET)
	public ModelAndView accessDenied(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
       if (principal != null) {
    	   modelAndView.addObject("message", "Hi " + principal.getName()
            + "<br> You do not have permission to access this page!");
	    } else {
	    	modelAndView.addObject("msg",
	                "You do not have permission to access this page!");
	    }
		modelAndView.setViewName("403Page");
		return modelAndView;
	}
	
	@RequestMapping(value={"/","/home"}, method=RequestMethod.GET)
	public ModelAndView home(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		Module module = moduleIntegration.findByName(SecurityConstants.MODULE_SECURITY);
		List<Permission> permissions= permissionIntegration.findEnabledListByModuleId(module.getId());
		
		List<Permission> list = new ArrayList<Permission>();
		for (Permission permission : permissions) {
			if (permission.getParentPermission()!=null) {
				list.add(permission);
			}
		}
//		modelAndView.addObject("permissions", list);
		session.setAttribute("permissions", list);
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
//	@RequestMapping(value={"/admin"}, method=RequestMethod.GET)
//	public ModelAndView admin(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("internal/adminPage");
//		return modelAndView;
//	}
	
}
