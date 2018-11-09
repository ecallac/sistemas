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

import com.ecallac.rentcar.domain.Conductor;
import com.ecallac.rentcar.domain.Vehiculo;
import com.ecallac.rentcar.domain.Alquiler;
import com.ecallac.rentcar.service.AlquilerService;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Constants;
import com.ecallac.rentcar.util.Status;
import com.ecallac.rentcar.util.Util;
import com.ecallac.rentcar.view.ConductorView;
import com.ecallac.rentcar.view.VehiculoView;
import com.ecallac.rentcar.view.AlquilerView;

/**
 * @author efrain.calla
 *
 */
@Controller
@RequestMapping(AlquilerController.CONTROL_NAME)
public class AlquilerController {
	@Autowired
	AlquilerService alquilerService;
	
	public static final String NAME="alquiler";
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
		List<AlquilerView> list = castObjectToObjectViewList(alquilerService.findList());
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<AlquilerView>());
		}
		return map;
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody AlquilerView view) {
        Map<String, Object> map = new HashMap<String, Object>();
        Alquiler object = alquilerService.findById(view.getId());
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        view = (AlquilerView)BeanParser.parseObjectToNewClass(object, AlquilerView.class, null);
        map.put("viewBean", view);
        return map;
    }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid AlquilerView view,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	        Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	        map.put(Status.STATUS_TXT.getCode(), Status.ERROR.getCode());
	        map.put("validated", false);
	        map.put("messages", errors);
	        return map;
	    }
		Alquiler object = (Alquiler) BeanParser.parseObjectToNewClass(view, Alquiler.class, null);
		if (object.getId()==null) {
			object.setCreatedBy(principal.getName());
		} else {
			object.setUpdatedBy(principal.getName());
		}
		alquilerService.save(object);
        
        map.put("validated", true);
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been saved successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody AlquilerView view,Principal principal){
		Map<String, Object> map = new HashMap<String, Object>();
		alquilerService.delete(view.getId(),principal.getName());
		map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been deleted successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	public List<AlquilerView> castObjectToObjectViewList(List<Alquiler> list){
		List<AlquilerView> newObjects = new ArrayList<AlquilerView>();
		for (Alquiler object : list) {
			AlquilerView view = (AlquilerView)BeanParser.parseObjectToNewClass(object, AlquilerView.class, null);
			newObjects.add(view);
		}
		return newObjects;
	}
	
	
}
