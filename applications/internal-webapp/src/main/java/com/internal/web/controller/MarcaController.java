/**
 * 
 */
package com.internal.web.controller;

import com.BeanParser;
import com.Utils;
import com.common.Marca;
import com.internal.web.beans.MarcaView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.MarcaIntegration;
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
@RequestMapping("/"+ MarcaController.NAME)
public class MarcaController {
	public static final String NAME="marca";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	MarcaIntegration marcaIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("marca");
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledMarcas", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableMarcas() {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Marca> list = marcaIntegration.findActivos();
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
		  List<Marca> list = marcaIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Marca>());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid MarcaView marcaView, BindingResult result, Principal principal) {
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

		try{

			Marca marca = (Marca) BeanParser.parseObjectToNewClass(marcaView, Marca.class, null);
			if (marca.getId()==null) {
				marca.setCreatedBy(principal.getName());
			} else {
				marca.setUpdatedBy(principal.getName());
			}
			marcaIntegration.save(marca);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody MarcaView marcaView) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Marca marca = marcaIntegration.findById(marcaView.getId());
			MarcaView marcaViewStored = (MarcaView)BeanParser.parseObjectToNewClass(marca, MarcaView.class, null);
			map.put("marcaView", marcaViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody MarcaView marcaView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Marca marca = (Marca) BeanParser.parseObjectToNewClass(marcaView, Marca.class, null);
			marca.setUpdatedBy(principal.getName());
			marcaIntegration.save(marca);
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
