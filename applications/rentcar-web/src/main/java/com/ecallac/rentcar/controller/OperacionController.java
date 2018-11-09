/**
 * 
 */
package com.ecallac.rentcar.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecallac.rentcar.component.OperacionFacade;
import com.ecallac.rentcar.domain.Alquiler;
import com.ecallac.rentcar.domain.Dinero;
import com.ecallac.rentcar.util.BeanParser;
import com.ecallac.rentcar.util.Constants;
import com.ecallac.rentcar.view.AlquilerView;
import com.ecallac.rentcar.view.DineroView;

/**
 * @author efrain
 *
 */
@Controller
@RequestMapping(OperacionController.CONTROL_NAME)
public class OperacionController {
	
	@Autowired
	OperacionFacade operacionFacade;
	
	public static final String NAME="operacion";
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
		List<DineroView> list = castObjectToObjectViewList(operacionFacade.findList());
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList<DineroView>());
		}
		return map;
	}
	
	public List<DineroView> castObjectToObjectViewList(List<Dinero> list){
		List<DineroView> newObjects = new ArrayList<DineroView>();
		for (Dinero object : list) {
			DineroView view = (DineroView)BeanParser.parseObjectToNewClass(object, DineroView.class, null);
			newObjects.add(view);
		}
		return newObjects;
	}
}
