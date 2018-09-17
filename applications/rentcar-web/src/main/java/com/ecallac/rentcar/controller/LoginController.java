/**
 * 
 */
package com.ecallac.rentcar.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecallac.rentcar.bean.NavigationLinkBean;
import com.ecallac.rentcar.service.LoginServiceImpl;
import com.ecallac.rentcar.util.Constants;


/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 14:03:23
 */
@Controller
public class LoginController {
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
	public ModelAndView home(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		request.getSession().setAttribute(Constants.LINKS_TXT.getCode(), LoginServiceImpl.getNavigationLinkList(new ArrayList<NavigationLinkBean>(), "/home", "Home", 0));
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	public static void setNavigationLinks(HttpServletRequest request,String controlName,String name,int level) {
		if (request.getSession().getAttribute(Constants.LINKS_TXT.getCode())!=null) {
			List<NavigationLinkBean> linkBeans = (List<NavigationLinkBean>) request.getSession().getAttribute(Constants.LINKS_TXT.getCode());
			request.getSession().setAttribute(Constants.LINKS_TXT.getCode(), LoginServiceImpl.getNavigationLinkList(linkBeans, controlName, StringUtils.capitalize(name), level));
		}
	}
	
}
