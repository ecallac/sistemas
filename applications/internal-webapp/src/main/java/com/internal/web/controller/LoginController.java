/**
 * 
 */
package com.internal.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.common.EntidadRolAtributo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.internal.web.service.LoginService;
import com.internal.web.utils.Constants;
import com.security.Module;
import com.security.Session;
import com.security.User;


/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 14:03:23
 */
@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(@RequestParam(value = "error",required = false) String error,@RequestParam(value = "logout",	required = false) String logout){
		ModelAndView modelAndView = new ModelAndView();
		if (error != null) {
			modelAndView.addObject("error", "Nombre de Usuario o clave es incorrecto!");
		}
		
//		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		
		
		if (logout != null) {
			modelAndView.addObject("message", "Desconectado de la página con éxito.");
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
		modelAndView.setViewName("403page");
		return modelAndView;
	}
	
	@RequestMapping(value={"/","/home"}, method=RequestMethod.GET)
	public ModelAndView home(HttpSession httpSession,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		loginService.addSessionObjects(httpSession,principal);
		
		modelAndView.setViewName("home");
		return modelAndView;
	}

	
	@RequestMapping(value = "/session/save", method = {RequestMethod.POST})
    public @ResponseBody Map<String, Object> saveSession(HttpSession httpSession,@RequestBody Session session,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("sessionKey:"+httpSession.getId());
		Module module = new Module();
		module.setName(Constants.MODULE);
		User user = new User();
		user.setUserName(principal.getName());
		session.setModule(module);
		session.setUser(user);
		session.setSessionKey(httpSession.getId());
		loginService.saveSession(session);
		map.put(Constants.STATUS, Constants.OK);
		return map;
    }

	@RequestMapping(value = "/session/saveTheme", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> saveSessionTheme(HttpSession httpSession, @RequestBody EntidadRolAtributo entidadRolAtributo, Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("sessionKey:"+httpSession.getId());
		loginService.addSessionObjects(httpSession,principal);
		loginService.saveEntidadRolAtributo(httpSession,entidadRolAtributo);
		map.put(Constants.STATUS, Constants.OK);
		return map;
	}
	
}
