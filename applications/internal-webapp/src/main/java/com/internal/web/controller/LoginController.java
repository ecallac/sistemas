/**
 * 
 */
package com.internal.web.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.Utils;
import com.common.EntidadRolAtributo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/login", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(
			@RequestParam(value = "error",required = false) String error,
			@RequestParam(value = "logout",	required = false) String logout,
			@RequestParam(value = "expired",	required = false) String expired,
			HttpSession httpSession, Principal principal, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		if (error != null) {
			modelAndView.addObject("error", "Nombre de Usuario o clave es incorrecto!");
		}

//		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		
		
		if (logout != null) {
			modelAndView.addObject("message", "Se ha cerrado la sesion con exito.");
			String sessionId = request.getSession().getId();
			String httpsessionId = httpSession.getId();
			Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
			Object details1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			if (details instanceof WebAuthenticationDetails) {
				WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
				String key = webDetails.getSessionId();
				Date expirationTime = new Date();
				// Hacer algo con la fecha y hora de expiración
				logger.info("session cerrada "+key + " a las " + Utils.dateToString(expirationTime,"yyyy-MM-dd"));
				System.out.println("session cerrada "+key + " a las " + Utils.dateToString(expirationTime,"yyyy-MM-dd"));
			}
		}
		if (expired != null) {
			modelAndView.addObject("message", "La sesion a expirado");
			Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			if (details instanceof WebAuthenticationDetails) {
				WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
				String key = webDetails.getSessionId();
				Date expirationTime = new Date();
				// Hacer algo con la fecha y hora de expiración
				logger.info("session expirada "+key + " a las " + Utils.dateToString(expirationTime,"yyyy-MM-dd"));
				System.out.println("session expirada "+key + " a las " + Utils.dateToString(expirationTime,"yyyy-MM-dd"));
			}
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
		try{
			loginService.addSessionObjects(httpSession,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("home");
		return modelAndView;
	}

	
	@RequestMapping(value = "/session/save", method = {RequestMethod.POST})
    public @ResponseBody Map<String, Object> saveSession(HttpSession httpSession,@RequestBody Session session,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			String sessionKey = httpSession.getId();
			String sessionKeySaved = (String) httpSession.getAttribute("sessionKeySaved");
			if (sessionKeySaved==null){
				logger.info("sessionKey:"+sessionKey+ " username:"+principal.getName());
				Module module = new Module();
				module.setName(Constants.MODULE);
				User user = new User();
				user.setUserName(principal.getName());
				session.setModule(module);
				session.setUser(user);
				session.setSessionKey(sessionKey);
				loginService.saveSession(session);
				httpSession.setAttribute("sessionKeySaved",httpSession.getId());
			}
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
		return map;
    }

	@RequestMapping(value = "/session/saveTheme", method = {RequestMethod.POST})
	public @ResponseBody Map<String, Object> saveSessionTheme(HttpSession httpSession, @RequestBody EntidadRolAtributo entidadRolAtributo, Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			loginService.addSessionObjects(httpSession,principal);
			loginService.saveEntidadRolAtributo(httpSession,entidadRolAtributo);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
		return map;
	}
	
}
