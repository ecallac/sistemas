/**
 * 
 */
package com.internal.web.controller;

import com.BeanParser;
import com.Utils;
import com.common.Area;
import com.internal.web.beans.AreaView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.AreaIntegration;
import com.internal.web.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author efrain
 *
 */
@Controller
@RequestMapping("/"+ AreaController.NAME)
public class AreaController {
	public static final String NAME="area";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	AreaIntegration areaIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("area");
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledAreas", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableAreas() {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Area> list = areaIntegration.findActivos();
			if (list != null) {
				map.put("data", list);
			} else {
				map.put("data", new ArrayList<>());
			}
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
		return map;
	}
	
  @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
	  try{
		  List<Area> list = areaIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Area>());
		  }
		  map.put(Constants.STATUS, Constants.OK);
	  }catch (Exception e){
		  logger.error(e.getMessage(),e);
		  map.put(Constants.STATUS, Constants.ERROR);
		  map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
	  }

      return map;
  }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid AreaView areaView, BindingResult result, Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(Constants.STATUS, Constants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	      }
		try {

			Area area = (Area) BeanParser.parseObjectToNewClass(areaView, Area.class, null);
			if (StringUtils.isNotBlank(areaView.getParentAreaId())) {
				area.setParentArea((Area)BeanParser.parseObjectToNewClass(areaView.getParentArea(), Area.class, null));
			}
			if (area.getId()==null) {
				area.setCreatedBy(principal.getName());
			} else {
				area.setUpdatedBy(principal.getName());
			}
			areaIntegration.save(area);

			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}finally {
			map.put("validated", true);
		}
        return map;
    }
	
	@RequestMapping(value = "/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody AreaView areaView) {
        Map<String, Object> map = new HashMap<String, Object>();
		try{
			Area area = areaIntegration.findById(areaView.getId());
			AreaView areaViewStored = (AreaView)BeanParser.parseObjectToNewClass(area, AreaView.class, null);
			areaViewStored.setParentArea((AreaView)BeanParser.parseObjectToNewClass(area.getParentArea(), AreaView.class, null));
			map.put("areaView", areaViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody AreaView areaView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Area area = (Area) BeanParser.parseObjectToNewClass(areaView, Area.class, null);
			area.setUpdatedBy(principal.getName());
			areaIntegration.save(area);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }
}
