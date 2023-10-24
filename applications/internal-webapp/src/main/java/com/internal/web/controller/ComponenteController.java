/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Componente;
import com.common.Marca;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.ComponenteView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.ComponenteIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.view.MarcaView;
import com.internal.web.view.TipoBaseView;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author efrain
 *
 */
@Controller
@RequestMapping("/"+ ComponenteController.NAME)
public class ComponenteController {
	public static final String NAME="componente";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	ComponenteIntegration componenteIntegration;
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
		modelAndView.setViewName("componente");
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledComponentes", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableComponentes() {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Componente> list = componenteIntegration.findActivos();
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
		  List<Componente> list = componenteIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Componente>());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ComponenteView view, BindingResult result, Principal principal) {
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

			Componente bean = (Componente) BeanParser.parseObjectToNewClass(view, Componente.class, null);
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			componenteIntegration.save(bean);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody ComponenteView view) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Componente bean = componenteIntegration.findById(view.getId());
			ComponenteView viewStored = (ComponenteView)BeanParser.parseObjectToNewClass(bean, ComponenteView.class, null);
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
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody ComponenteView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				componenteIntegration.save(castViewBeanToBaseList(view,principal));
			}else{
				Componente bean = (Componente) BeanParser.parseObjectToNewClass(view, Componente.class, null);
				bean.setUpdatedBy(principal.getName());
				componenteIntegration.save(bean);
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
	public List<Componente> castViewBeanToBaseList(ComponenteView view, Principal principal){
		List<Componente> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Componente bean = (Componente) BeanParser.parseObjectToNewClass(view, Componente.class, null);
			bean.setId(Long.valueOf(id));
			bean.setUpdatedBy(principal.getName());
			list.add(bean);
		}
		return list;
	}
	@RequestMapping(value = "/verifyNombre", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> verifyNombre(@RequestParam(value = "nombre",required = true) String nombre) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Componente bean = componenteIntegration.findByNombre(nombre);
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
			DataTablesInput<Componente> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Componente bean = new Componente();
			bean.setStatus((String) parameterMap.get("status"));
			dataTablesInput.setObject(bean);

			DataTablesOutput<Componente> dataTablesOutput = componenteIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castComponenteToComponenteViewList(dataTablesOutput.getData()));
				map.put("draw", dataTablesInput.getDraw());
				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
			} else {
				map.put("data", new ArrayList<Componente>());
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

	public List<ComponenteView> castComponenteToComponenteViewList(List<Componente> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			ComponenteView view =(ComponenteView)BeanParser.parseObjectToNewClass(bean,ComponenteView.class,null);
			view.setTipoComponenteDescripcion(tipoBaseMap.containsKey(view.getTipoComponente())?tipoBaseMap.get(view.getTipoComponente()).getDescripcion():null);
			view.setStatusDescripcion(tipoBaseMap.containsKey(view.getStatus())?tipoBaseMap.get(view.getStatus()).getDescripcion():null);
			view.setStatusType(reglaDetalleMap.containsKey(view.getStatus())?reglaDetalleMap.get(view.getStatus()).getValorcadena():null);
			return view;
		}).collect(Collectors.toList());
	}
}
