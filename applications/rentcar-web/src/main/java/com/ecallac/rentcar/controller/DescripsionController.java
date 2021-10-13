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

import com.ecallac.rentcar.domain.Descripsion;
import com.ecallac.rentcar.service.DescripsionService;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Constants;
import com.ecallac.rentcar.util.Status;
import com.ecallac.rentcar.util.Util;
import com.ecallac.rentcar.view.DescripsionView;

/**
 * @author efrain.calla
 *
 */
@Controller
@RequestMapping(DescripsionController.CONTROL_NAME)
public class DescripsionController {
	@Autowired
	DescripsionService descripsionService;
	
	public static final String NAME="descripsion";
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
		List<DescripsionView> list = castObjectToObjectViewList(descripsionService.findList());
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<DescripsionView>());
		}
		return map;
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody DescripsionView view) {
        Map<String, Object> map = new HashMap<String, Object>();
        Descripsion object = descripsionService.findById(view.getId());
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        view = (DescripsionView)BeanParser.parseObjectToNewClass(object, DescripsionView.class, null);
        map.put("viewBean", view);
        return map;
    }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid DescripsionView view,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	        Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	        map.put(Status.STATUS_TXT.getCode(), Status.ERROR.getCode());
	        map.put("validated", false);
	        map.put("messages", errors);
	        return map;
	    }
		Descripsion object = (Descripsion) BeanParser.parseObjectToNewClass(view, Descripsion.class, null);
		if (object.getId()==null) {
			object.setCreatedBy(principal.getName());
		} else {
			object.setUpdatedBy(principal.getName());
		}
		descripsionService.save(object);
        
        map.put("validated", true);
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been saved successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody DescripsionView view,Principal principal){
		Map<String, Object> map = new HashMap<String, Object>();
		descripsionService.delete(view.getId(),principal.getName());
		map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been deleted successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	public List<DescripsionView> castObjectToObjectViewList(List<Descripsion> list){
		List<DescripsionView> newObjects = new ArrayList<DescripsionView>();
		for (Descripsion object : list) {
			DescripsionView view = (DescripsionView)BeanParser.parseObjectToNewClass(object, DescripsionView.class, null);
			newObjects.add(view);
		}
		return newObjects;
	}
	
	
}
