/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.*;
import com.common.ReglaDetalle;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.ReglaDetalleView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
@RequestMapping("/"+ ReglaDetalleController.NAME)
public class ReglaDetalleController {
	public static final String NAME="reglaDetalle";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	ReglaDetalleIntegration reglaDetalleIntegration;
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session, Principal principal,HttpServletRequest request,@RequestParam(value = "reglaId",required = true) String reglaId){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("reglaDetalle");
		request.setAttribute("reglaId", reglaId);
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledReglaDetalles", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableReglaDetalles() {
		Map<String, Object> map = new HashMap<>();
		try{
			List<ReglaDetalle> list = reglaDetalleIntegration.findActivos();
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

	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ReglaDetalleView view, BindingResult result, Principal principal) {
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

			ReglaDetalle reglaDetalle = (ReglaDetalle) BeanParser.parseObjectToNewClass(view, ReglaDetalle.class, null);
			if (reglaDetalle.getId()==null) {
				reglaDetalle.setCreatedBy(principal.getName());
			} else {
				reglaDetalle.setUpdatedBy(principal.getName());
			}
			reglaDetalleIntegration.save(reglaDetalle);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody ReglaDetalleView view) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			ReglaDetalle reglaDetalle = reglaDetalleIntegration.findById(view.getId());
			ReglaDetalleView reglaDetalleViewStored = (ReglaDetalleView)BeanParser.parseObjectToNewClass(reglaDetalle, ReglaDetalleView.class, null);
			map.put("viewBean", reglaDetalleViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody ReglaDetalleView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				reglaDetalleIntegration.save(castViewBeanToBaseList(view,principal));
			}else {
				ReglaDetalle reglaDetalle = (ReglaDetalle) BeanParser.parseObjectToNewClass(view, ReglaDetalle.class, null);
				reglaDetalle.setUpdatedBy(principal.getName());
				reglaDetalleIntegration.save(reglaDetalle);
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
	public List<ReglaDetalle> castViewBeanToBaseList(ReglaDetalleView view, Principal principal){
		List<ReglaDetalle> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			ReglaDetalle bean = (ReglaDetalle) BeanParser.parseObjectToNewClass(view, ReglaDetalle.class, null);
			bean.setId(Long.valueOf(id));
			bean.setUpdatedBy(principal.getName());
			list.add(bean);
		}
		return list;
	}

	@RequestMapping(value = "/findByPage", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> findByPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> parameterMap = InternalUtils.getParameterMap(request);
			DataTablesInput<ReglaDetalle> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			ReglaDetalle bean = new ReglaDetalle();
			bean.setActivo((String) parameterMap.get("status"));
			Regla regla = new Regla();
			regla.setId(Long.valueOf((String) parameterMap.get("reglaId")));
			bean.setRegla(regla);
			dataTablesInput.setObject(bean);

			DataTablesOutput<ReglaDetalle> dataTablesOutput = reglaDetalleIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castReglaDetalleToReglaDetalleViewList(dataTablesOutput.getData()));
				map.put("draw", dataTablesOutput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<ReglaDetalle>());
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
	public List<ReglaDetalleView> castReglaDetalleToReglaDetalleViewList(List<ReglaDetalle> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			ReglaDetalleView view =(ReglaDetalleView)BeanParser.parseObjectToNewClass(bean,ReglaDetalleView.class,null);
			view.setActivoDescripcion(tipoBaseMap.containsKey(view.getActivo())?tipoBaseMap.get(view.getActivo()).getDescripcion():null);
			view.setActivoType(reglaDetalleMap.containsKey(view.getActivo())?reglaDetalleMap.get(view.getActivo()).getValorcadena():null);
			return view;
		}).collect(Collectors.toList());
	}
}
