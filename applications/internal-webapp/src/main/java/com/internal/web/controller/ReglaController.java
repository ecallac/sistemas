/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Regla;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.ReglaIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.ReglaView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author efrain
 *
 */
@Controller
@RequestMapping("/"+ ReglaController.NAME)
public class ReglaController {
	public static final String NAME="regla";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	ReglaIntegration reglaIntegration;
	@Autowired
	ReglaDetalleIntegration reglaDetalleIntegration;
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
		modelAndView.setViewName(ReglaController.NAME);
		return modelAndView;
	}
	
//	@RequestMapping(value = "/enabledReglas", method = {RequestMethod.GET,RequestMethod.POST})
//	public @ResponseBody Map<String, Object> initializeEnableReglas() {
//		Map<String, Object> map = new HashMap<>();
//		try {
//			List<Regla> list = reglaIntegration.findActivos();
//			if (list != null) {
//				map.put("data", list);
//			} else {
//				map.put("data", new ArrayList<>());
//			}
//			map.put(Constants.STATUS, Constants.OK);
//		}catch (Exception e){
//			logger.error(e.getMessage(),e);
//			map.put(Constants.STATUS, Constants.ERROR);
//			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
//		}
//		return map;
//	}
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ReglaView view, BindingResult result, Principal principal) {
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

			Regla bean = (Regla) BeanParser.parseObjectToNewClass(view, Regla.class, null);
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			reglaIntegration.save(bean);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody ReglaView view) {
        Map<String, Object> map = new HashMap<String, Object>();
		try{
			Regla bean = reglaIntegration.findById(view.getId());
			ReglaView viewStored = (ReglaView)BeanParser.parseObjectToNewClass(bean, ReglaView.class, null);
			map.put("reglaView", viewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody ReglaView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Regla bean = (Regla) BeanParser.parseObjectToNewClass(view, Regla.class, null);
			bean.setUpdatedBy(principal.getName());
			reglaIntegration.save(bean);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }
	@RequestMapping(value = "/verifyCodigo", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> verifyNombre(@RequestParam(value = "codigo",required = true) String codigo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Regla bean = reglaIntegration.findByCodigo(codigo);
			if (bean!=null){
				map.put(Constants.STATUS, Constants.OK);
			}else{
				map.put(Constants.STATUS, Constants.ERROR);
				map.put(Constants.MESSAGE, Constants.ERROR_MESSAGE_EXIST);
			}

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
		return map;
	}

	@RequestMapping(value = "/findByPage", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> findByPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> parameterMap = InternalUtils.getParameterMap(request);
			DataTablesInput<Regla> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Regla bean = new Regla();
			bean.setActivo((String) parameterMap.get("activo"));
			dataTablesInput.setObject(bean);

			DataTablesOutput<Regla> dataTablesOutput = tipoBaseIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castReglaToReglaViewList(dataTablesOutput.getData()));
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
	public List<ReglaView> castReglaToReglaViewList(List<Regla> list) throws Exception {
		Map<String,ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			ReglaView view =(ReglaView)BeanParser.parseObjectToNewClass(bean,ReglaView.class,null);
			view.setActivoDescripcion(tipoBaseMap.containsKey(view.getActivo())?tipoBaseMap.get(view.getActivo()).getDescripcion():null);
			view.setActivoType(reglaDetalleMap.containsKey(view.getActivo())?reglaDetalleMap.get(view.getActivo()).getValorcadena():null);
			return view;
		}).collect(Collectors.toList());
	}
}
