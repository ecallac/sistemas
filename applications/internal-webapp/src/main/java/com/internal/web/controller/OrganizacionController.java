/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Entidad;
import com.common.Organizacion;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.common.cont.TipoBaseConst;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.OrganizacionIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.OrganizacionView;
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
@RequestMapping("/"+ OrganizacionController.NAME)
public class OrganizacionController {
	public static final String NAME="organizacion";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	OrganizacionIntegration organizacionIntegration;
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	@Autowired
	ReglaDetalleIntegration reglaDetalleIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("organizacion");
		return modelAndView;
	}
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid OrganizacionView view, BindingResult result, Principal principal) {
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

			Organizacion bean = (Organizacion) BeanParser.parseObjectToNewClass(view, Organizacion.class, null);
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
				TipoBase tipoBase = tipoBaseIntegration.findByCodigo(TipoBaseConst.TIPOBASE_CODIGO_ORGANIZACION.getCode());
				Entidad entidad = new Entidad();
				entidad.setCreatedBy(principal.getName());
				entidad.setTipoEntidad(tipoBase.getCodigo());
				bean.setEntidad(entidad);
				organizacionIntegration.saveEntidad(bean);
			} else {
				bean.setUpdatedBy(principal.getName());
				organizacionIntegration.save(bean);
			}


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
    public @ResponseBody  Map<String, Object> load(@RequestBody OrganizacionView view) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Organizacion bean = organizacionIntegration.findById(view.getId());
			OrganizacionView viewStored = (OrganizacionView)BeanParser.parseObjectToNewClass(bean, OrganizacionView.class, null);
			map.put("viewBean", viewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody OrganizacionView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				organizacionIntegration.save(castViewBeanToBaseList(view,principal));
			}else{
				Organizacion bean = (Organizacion) BeanParser.parseObjectToNewClass(view, Organizacion.class, null);
				bean.setUpdatedBy(principal.getName());
				organizacionIntegration.save(bean);
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
	public List<Organizacion> castViewBeanToBaseList(OrganizacionView view, Principal principal){
		List<Organizacion> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Organizacion bean = (Organizacion) BeanParser.parseObjectToNewClass(view, Organizacion.class, null);
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
			DataTablesInput<Organizacion> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Organizacion bean = new Organizacion();
			bean.setStatus((String) parameterMap.get("status"));
			bean.setTipoOrganizacion((String) parameterMap.get("tipoOrganizacion"));
			dataTablesInput.setObject(bean);

			DataTablesOutput<Organizacion> dataTablesOutput = organizacionIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castOrganizacionToOrganizacionViewList(dataTablesOutput.getData()));
				map.put("draw", dataTablesInput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<Organizacion>());
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

	public List<OrganizacionView> castOrganizacionToOrganizacionViewList(List<Organizacion> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			OrganizacionView view =(OrganizacionView)BeanParser.parseObjectToNewClass(bean,OrganizacionView.class,null);
			view.setTipoOrganizacionDescripcion(tipoBaseMap.containsKey(view.getTipoOrganizacion())?tipoBaseMap.get(view.getTipoOrganizacion()).getDescripcion():null);
			view.setStatusDescripcion(tipoBaseMap.containsKey(view.getStatus())?tipoBaseMap.get(view.getStatus()).getDescripcion():null);
			view.setStatusType(reglaDetalleMap.containsKey(view.getStatus())?reglaDetalleMap.get(view.getStatus()).getValorcadena():null);
			return view;
		}).collect(Collectors.toList());
	}
}
