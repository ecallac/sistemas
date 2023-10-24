/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Entidad;
import com.common.Persona;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.common.cont.TipoBaseConst;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.PersonaIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.PersonaView;
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
@RequestMapping("/"+ PersonaController.NAME)
public class PersonaController {
	public static final String NAME="persona";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	PersonaIntegration personaIntegration;
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
		modelAndView.setViewName("persona");
		return modelAndView;
	}
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid PersonaView view, BindingResult result, Principal principal) {
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

			Persona bean = (Persona) BeanParser.parseObjectToNewClass(view, Persona.class, null);
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
				TipoBase tipoBase = tipoBaseIntegration.findByCodigo(TipoBaseConst.TIPOBASE_CODIGO_PERSONA.getCode());
				Entidad entidad = new Entidad();
				entidad.setCreatedBy(principal.getName());
				entidad.setTipoEntidad(tipoBase.getCodigo());
				bean.setEntidad(entidad);
				bean.setFechanacimiento(Utils.stringToDate(view.getFechanacimiento(), PersonaView.FORMAT));
				personaIntegration.saveEntidad(bean);
			} else {
				bean.setUpdatedBy(principal.getName());
				personaIntegration.save(bean);
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
    public @ResponseBody  Map<String, Object> load(@RequestBody PersonaView view) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Persona bean = personaIntegration.findById(view.getId());
			PersonaView viewStored = (PersonaView)BeanParser.parseObjectToNewClass(bean, PersonaView.class, null);
			viewStored.setFechanacimiento(Utils.dateToString(bean.getFechanacimiento(), PersonaView.FORMAT));
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
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody PersonaView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				personaIntegration.save(castViewBeanToBaseList(view,principal));
			}else{
				Persona bean = (Persona) BeanParser.parseObjectToNewClass(view, Persona.class, null);
				bean.setUpdatedBy(principal.getName());
				personaIntegration.save(bean);
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
	public List<Persona> castViewBeanToBaseList(PersonaView view, Principal principal){
		List<Persona> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Persona bean = (Persona) BeanParser.parseObjectToNewClass(view, Persona.class, null);
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
			DataTablesInput<Persona> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Persona bean = new Persona();
			bean.setStatus((String) parameterMap.get("status"));
			bean.setTipoDocumentoIdentificaion((String) parameterMap.get("tipoDocumentoIdentificaion"));
			bean.setTipoEstadoCivil((String) parameterMap.get("tipoEstadoCivil"));
			dataTablesInput.setObject(bean);

			DataTablesOutput<Persona> dataTablesOutput = personaIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castPersonaToPersonaViewList(dataTablesOutput.getData()));
				map.put("draw", dataTablesInput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<Persona>());
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

	public List<PersonaView> castPersonaToPersonaViewList(List<Persona> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			PersonaView view =(PersonaView)BeanParser.parseObjectToNewClass(bean,PersonaView.class,null);
			view.setFechanacimiento(Utils.dateToString(bean.getFechanacimiento(), PersonaView.FORMAT));
			view.setTipoDocumentoIdentificaionDescripcion(tipoBaseMap.containsKey(view.getTipoDocumentoIdentificaion())?tipoBaseMap.get(view.getTipoDocumentoIdentificaion()).getDescripcion():null);
			view.setTipoEstadoCivilDescripcion(tipoBaseMap.containsKey(view.getTipoEstadoCivil())?tipoBaseMap.get(view.getTipoEstadoCivil()).getDescripcion():null);
			view.setStatusDescripcion(tipoBaseMap.containsKey(view.getStatus())?tipoBaseMap.get(view.getStatus()).getDescripcion():null);
			view.setStatusType(reglaDetalleMap.containsKey(view.getStatus())?reglaDetalleMap.get(view.getStatus()).getValorcadena():null);
			return view;
		}).collect(Collectors.toList());
	}
}
