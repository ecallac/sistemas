/**
 * 
 */
package com.ecallac.rentcar.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecallac.rentcar.domain.Clase;
import com.ecallac.rentcar.service.ClaseService;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Constants;
import com.ecallac.rentcar.util.Status;
import com.ecallac.rentcar.util.Util;
import com.ecallac.rentcar.view.ClaseView;

/**
 * @author efrain.calla
 *
 */
@Controller
@RequestMapping(ClaseController.CONTROL_NAME)
public class ClaseController {
	@Autowired
	ClaseService claseService;
	
	public static final String NAME="clase";
	public static final String CONTROL_NAME="/"+NAME;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(NAME);
		modelAndView.addObject(Constants.TITTLE_TXT.getCode(), StringUtils.capitalize(NAME));
		LoginController.setNavigationLinks(request, CONTROL_NAME, NAME, 1);
		return modelAndView;
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> getAll() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ClaseView> list = castObjectToObjectViewList(claseService.findList());
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<ClaseView>());
		}
		return map;
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody ClaseView view) {
        Map<String, Object> map = new HashMap<String, Object>();
        Clase module = claseService.findById(view.getId());
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put("viewBean", (ClaseView)BeanParser.parseObjectToNewClass(module, ClaseView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ClaseView view,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	        Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	        map.put(Status.STATUS_TXT.getCode(), Status.ERROR.getCode());
	        map.put("validated", false);
	        map.put("messages", errors);
	        return map;
	    }
		Clase object = (Clase) BeanParser.parseObjectToNewClass(view, Clase.class, null);
		if (object.getId()==null) {
			object.setCreatedBy(principal.getName());
		} else {
			object.setUpdatedBy(principal.getName());
		}
		claseService.save(object);
        
        map.put("validated", true);
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been saved successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody ClaseView view,Principal principal){
		Map<String, Object> map = new HashMap<String, Object>();
		claseService.delete(view.getId(),principal.getName());
		map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been deleted successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	public List<ClaseView> castObjectToObjectViewList(List<Clase> list){
		List<ClaseView> newObjects = new ArrayList<ClaseView>();
		for (Clase object : list) {
			newObjects.add((ClaseView)BeanParser.parseObjectToNewClass(object, ClaseView.class, null));
		}
		return newObjects;
	}
	
	
}
