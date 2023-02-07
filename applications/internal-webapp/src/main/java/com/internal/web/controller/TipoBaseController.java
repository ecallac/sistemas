/**
 * 
 */
package com.internal.web.controller;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.Utils;
import com.common.TipoBase;
import com.internal.web.beans.TipoBaseView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
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

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/"+ TipoBaseController.NAME)
public class TipoBaseController {
	public static final String NAME="tipoBase";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("tipoBase");
		return modelAndView;
	}
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid TipoBaseView tipoBaseView, BindingResult result, Principal principal) {
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

			TipoBase tipoBase = (TipoBase) BeanParser.parseObjectToNewClass(tipoBaseView, TipoBase.class, null);
			if (tipoBase.getId()==null) {
				tipoBase.setCreatedBy(principal.getName());
			} else {
				tipoBase.setUpdatedBy(principal.getName());
			}
			tipoBaseIntegration.save(tipoBase);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody TipoBaseView tipoBaseView) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			TipoBase tipoBase = tipoBaseIntegration.findById(tipoBaseView.getId());
			TipoBaseView tipoBaseViewStored = (TipoBaseView)BeanParser.parseObjectToNewClass(tipoBase, TipoBaseView.class, null);
			map.put("tipoBaseView", tipoBaseViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody TipoBaseView tipoBaseView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			TipoBase tipoBase = (TipoBase) BeanParser.parseObjectToNewClass(tipoBaseView, TipoBase.class, null);
			tipoBase.setUpdatedBy(principal.getName());
			tipoBaseIntegration.save(tipoBase);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }

	@RequestMapping(value = "/findByPage", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> findByPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> parameterMap = InternalUtils.getParameterMap(request);
			DataTablesInput<TipoBase> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			TipoBase tipoBase = new TipoBase();
			tipoBase.setActivo((String) parameterMap.get("activo"));
			dataTablesInput.setObject(tipoBase);

			DataTablesOutput<TipoBase> dataTablesOutput = tipoBaseIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", dataTablesOutput.getData());
				map.put("draw", dataTablesOutput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<TipoBase>());
			}

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
