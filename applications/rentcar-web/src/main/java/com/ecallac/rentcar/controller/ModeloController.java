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

import com.ecallac.rentcar.domain.Marca;
import com.ecallac.rentcar.domain.Modelo;
import com.ecallac.rentcar.service.ModeloService;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Constants;
import com.ecallac.rentcar.util.Status;
import com.ecallac.rentcar.util.Util;
import com.ecallac.rentcar.view.MarcaView;
import com.ecallac.rentcar.view.ModeloView;

/**
 * @author efrain.calla
 *
 */
@Controller
@RequestMapping(ModeloController.CONTROL_NAME)
public class ModeloController {
	@Autowired
	ModeloService modeloService;
	
	public static final String NAME="modelo";
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
		List<ModeloView> list = castObjectToObjectViewList(modeloService.findList());
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<ModeloView>());
		}
		return map;
	}
	
	@RequestMapping(value = "/listByMarca", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> getListByMarca(@RequestBody ModeloView view) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ModeloView> list = castObjectToObjectViewList(modeloService.findListByMarca(view.getMarca().getId()));
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<ModeloView>());
		}
		return map;
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody ModeloView view) {
        Map<String, Object> map = new HashMap<String, Object>();
        Modelo object = modeloService.findById(view.getId());
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        view = (ModeloView)BeanParser.parseObjectToNewClass(object, ModeloView.class, null);
        map.put("viewBean", view);
        return map;
    }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ModeloView view,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	        Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
	        map.put(Status.STATUS_TXT.getCode(), Status.ERROR.getCode());
	        map.put("validated", false);
	        map.put("messages", errors);
	        return map;
	    }
		Modelo object = (Modelo) BeanParser.parseObjectToNewClass(view, Modelo.class, null);
		if (object.getId()==null) {
			object.setCreatedBy(principal.getName());
		} else {
			object.setUpdatedBy(principal.getName());
		}
		modeloService.save(object);
        
        map.put("validated", true);
        map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been saved successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
	@RequestMapping(value={"/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody ModeloView view,Principal principal){
		Map<String, Object> map = new HashMap<String, Object>();
		modeloService.delete(view.getId(),principal.getName());
		map.put(Status.STATUS_TXT.getCode(), Status.OK.getCode());
        map.put(Status.MESSAGE_TXT.getCode(), "Your record have been deleted successfully at "+Util.convertDateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
	
	public List<ModeloView> castObjectToObjectViewList(List<Modelo> list){
		List<ModeloView> newObjects = new ArrayList<ModeloView>();
		for (Modelo object : list) {
			ModeloView view = (ModeloView)BeanParser.parseObjectToNewClass(object, ModeloView.class, null);
			newObjects.add(view);
		}
		return newObjects;
	}
	
	
}
