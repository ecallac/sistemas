/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.*;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.EmpleadoIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.EmpleadoView;
import com.internal.web.view.PersonaView;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/"+ EmpleadoController.NAME)
public class EmpleadoController {
	public static final String NAME="empleado";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	EmpleadoIntegration empleadoIntegration;
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
		modelAndView.setViewName(EmpleadoController.NAME);
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledEmpleados", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableEmpleados() {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Empleado> list = empleadoIntegration.findActivos();
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
		  List<Empleado> list = empleadoIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Empleado>());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid EmpleadoView empleadoView, BindingResult result, Principal principal) {
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
			Empleado empleado = (Empleado) BeanParser.parseObjectToNewClass(empleadoView, Empleado.class, null);
			if (!ObjectUtils.isEmpty(empleadoView.getPersona())) {
				empleado.setPersona((Persona) BeanParser.parseObjectToNewClass(empleadoView.getPersona(), Persona.class, null));
			}
			if (!ObjectUtils.isEmpty(empleadoView.getReporta())) {
				empleado.setReporta((Empleado) BeanParser.parseObjectToNewClass(empleadoView.getReporta(), Empleado.class, null));
			}
			if (!ObjectUtils.isEmpty(empleadoView.getSupervisorausencias())) {
				empleado.setSupervisorausencias((Empleado) BeanParser.parseObjectToNewClass(empleadoView.getSupervisorausencias(), Empleado.class, null));
			}
			if (empleado.getId()==null) {
				empleado.setCreatedBy(principal.getName());
			} else {
				empleado.setUpdatedBy(principal.getName());
			}
			empleadoIntegration.save(empleado);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody EmpleadoView empleadoView) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Empleado empleado = empleadoIntegration.findById(empleadoView.getId());
			EmpleadoView empleadoViewStored = (EmpleadoView)BeanParser.parseObjectToNewClass(empleado, EmpleadoView.class, null);
			empleadoViewStored.setPersona((PersonaView) BeanParser.parseObjectToNewClass(empleado.getPersona(), PersonaView.class, null));
			empleadoViewStored.setReporta((EmpleadoView)BeanParser.parseObjectToNewClass(empleado.getReporta(), EmpleadoView.class, null));
			empleadoViewStored.setSupervisorausencias((EmpleadoView)BeanParser.parseObjectToNewClass(empleado.getSupervisorausencias(), EmpleadoView.class, null));
			map.put("viewBean", empleadoViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody EmpleadoView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				empleadoIntegration.save(castViewBeanToBaseList(view,principal));
			}else {

			}
			Empleado empleado = (Empleado) BeanParser.parseObjectToNewClass(view, Empleado.class, null);
			empleado.setUpdatedBy(principal.getName());
			empleadoIntegration.save(empleado);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }
	public List<Empleado> castViewBeanToBaseList(EmpleadoView view, Principal principal){
		List<Empleado> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Empleado bean = (Empleado) BeanParser.parseObjectToNewClass(view, Empleado.class, null);
			bean.setId(Long.valueOf(id));
			bean.setUpdatedBy(principal.getName());
			list.add(bean);
		}
		return list;
	}
	@RequestMapping(value = "/verifyCodigo", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> verifyCodigo(@RequestParam(value = "codigo",required = true) String codigo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Empleado bean = empleadoIntegration.findByCodigo(codigo);
			if (bean==null){
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
			DataTablesInput<Empleado> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Empleado bean = new Empleado();
			bean.setEstado((String) parameterMap.get("estado"));
			Empleado reporta = new Empleado();
			Optional.ofNullable((String) parameterMap.get("reportaId")).filter(StringUtils::isNotBlank)
					.ifPresent(parentId -> reporta.setId(Long.valueOf((String) parentId)));
			bean.setReporta(reporta);
			Empleado supervisorausencias = new Empleado();
			Optional.ofNullable((String) parameterMap.get("supervisorausenciasId")).filter(StringUtils::isNotBlank)
					.ifPresent(parentId -> supervisorausencias.setId(Long.valueOf((String) parentId)));
			bean.setSupervisorausencias(supervisorausencias);

			dataTablesInput.setObject(bean);

			DataTablesOutput<Empleado> dataTablesOutput = empleadoIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castEmpleadoToEmpleadoViewList(dataTablesOutput.getData()));
				map.put("draw", dataTablesOutput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<Area>());
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
	public List<EmpleadoView> castEmpleadoToEmpleadoViewList(List<Empleado> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			EmpleadoView view =(EmpleadoView)BeanParser.parseObjectToNewClass(bean,EmpleadoView.class,null);
			view.setEstadoDescripcion(tipoBaseMap.containsKey(view.getEstado())?tipoBaseMap.get(view.getEstado()).getDescripcion():null);
			view.setEstadoType(reglaDetalleMap.containsKey(view.getEstado())?reglaDetalleMap.get(view.getEstado()).getValorcadena():null);
			view.setReporta((EmpleadoView) BeanParser.parseObjectToNewClass(bean.getReporta(),EmpleadoView.class,null));
			view.setSupervisorausencias((EmpleadoView) BeanParser.parseObjectToNewClass(bean.getSupervisorausencias(),EmpleadoView.class,null));
			view.setPersona((PersonaView) BeanParser.parseObjectToNewClass(bean.getPersona(), PersonaView.class,null));
			return view;
		}).collect(Collectors.toList());
	}
}
