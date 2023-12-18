/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.*;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.*;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.DireccionView;
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
@RequestMapping("/"+ DireccionController.NAME)
public class DireccionController {
	public static final String NAME="direccion";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	DireccionIntegration direccionIntegration;
	@Autowired
	OrganizacionIntegration organizacionIntegration;
	@Autowired
	ReglaDetalleIntegration reglaDetalleIntegration;
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	@Autowired
	EntidadIntegration entidadIntegration;
	@Autowired
	UbigeoIntegration ubigeoIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName(DireccionController.NAME);
		return modelAndView;
	}
	
//	@RequestMapping(value = "/enabledDireccions", method = {RequestMethod.GET,RequestMethod.POST})
//	public @ResponseBody Map<String, Object> initializeEnableDireccions() {
//		Map<String, Object> map = new HashMap<>();
//		try{
//			List<Direccion> list = direccionIntegration.findActivos();
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
	@RequestMapping(value = "/enabledDireccions", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableDireccions(@RequestParam(value = "parentId", required = true) Long organizacionId) {
		Map<String, Object> map = new HashMap<>();
		try{
			Organizacion organizacion = organizacionIntegration.findById(organizacionId);
			List<Direccion> list = direccionIntegration.findEnabledByEntidadId(organizacion.getEntidad().getId());
			if (list != null) {
				map.put("data", list);
			} else {
				map.put("data", new ArrayList<>());
			}
//			map.put("entidadId", organizacion.getEntidad().getId());
			map.put("organizacion", organizacion);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
		return map;
	}
//
//  @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
//  public @ResponseBody Map<String, Object> getAll() {
//      Map<String, Object> map = new HashMap<String, Object>();
//	  try{
//		  List<Direccion> list = direccionIntegration.findList();
//		  if (list != null) {
//			  map.put("data", list);
//		  } else {
//			  map.put("data", new ArrayList<Direccion>());
//		  }
//		  map.put(Constants.STATUS, Constants.OK);
//	  }catch (Exception e){
//		  logger.error(e.getMessage(),e);
//		  map.put(Constants.STATUS, Constants.ERROR);
//		  map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
//	  }
//      return map;
//  }
//
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid DireccionView view, BindingResult result, Principal principal) {
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
			Direccion bean = (Direccion) BeanParser.parseObjectToNewClass(view, Direccion.class, null);
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			Entidad entidad = new Entidad();
			entidad.setId(Long.valueOf(view.getEntidadId()));
			bean.setEntidad(entidad);
			Ubigeo ubigeo = new Ubigeo();
			ubigeo.setId(Long.valueOf(view.getUbigeoId()));
			bean.setUbigeo(ubigeo);
			direccionIntegration.save(bean);

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
//
//	@RequestMapping(value = "/load", method = {RequestMethod.POST})
//    public @ResponseBody  Map<String, Object> load(@RequestBody DireccionView view) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//		try{
//			Direccion direccion = direccionIntegration.findById(view.getId());
//			DireccionView viewStored = (DireccionView)BeanParser.parseObjectToNewClass(direccion, DireccionView.class, null);
//			viewStored.setParentDireccion((DireccionView)BeanParser.parseObjectToNewClass(direccion.getParentDireccion(), DireccionView.class, null));
//			map.put("viewBean", viewStored);
//			map.put(Constants.STATUS, Constants.OK);
//		}catch (Exception e){
//			logger.error(e.getMessage(),e);
//			map.put(Constants.STATUS, Constants.ERROR);
//			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
//		}
//        return map;
//    }
//
//	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
//    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody DireccionView view,Principal principal) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//		try{
//			if (view.getIds()!=null){
//				direccionIntegration.save(castViewBeanToBaseList(view,principal));
//			}else {
//
//			}
//			Direccion direccion = (Direccion) BeanParser.parseObjectToNewClass(view, Direccion.class, null);
//			direccion.setUpdatedBy(principal.getName());
//			direccionIntegration.save(direccion);
//			map.put(Constants.STATUS, Constants.OK);
//			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));
//
//		}catch (Exception e){
//			logger.error(e.getMessage(),e);
//			map.put(Constants.STATUS, Constants.ERROR);
//			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
//		}
//        return map;
//    }
//	public List<Direccion> castViewBeanToBaseList(DireccionView view, Principal principal){
//		List<Direccion> list = new ArrayList<>();
//		for (int i = 0; i < view.getIds().length; i++) {
//			String id = view.getIds()[i];
//			Direccion bean = (Direccion) BeanParser.parseObjectToNewClass(view, Direccion.class, null);
//			bean.setId(Long.valueOf(id));
//			bean.setUpdatedBy(principal.getName());
//			list.add(bean);
//		}
//		return list;
//	}
//	@RequestMapping(value = "/verifyNombre", method = {RequestMethod.POST,RequestMethod.GET})
//	public @ResponseBody  Map<String, Object> verifyNombre(@RequestParam(value = "nombre",required = true) String nombre) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		try{
//			Direccion bean = direccionIntegration.findByNombre(nombre);
//			if (bean==null){
//				map.put(Constants.STATUS, Constants.OK);
//			}else{
//				map.put(Constants.STATUS, Constants.ERROR);
//				map.put(Constants.MESSAGE, Constants.ERROR_MESSAGE_EXIST);
//			}
//
//		}catch (Exception e){
//			logger.error(e.getMessage(),e);
//			map.put(Constants.STATUS, Constants.ERROR);
//			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
//		}
//		return map;
//	}
//
//	@RequestMapping(value = "/findByPage", method = {RequestMethod.POST,RequestMethod.GET})
//	public @ResponseBody  Map<String, Object> findByPage(HttpServletRequest request) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		try{
//			Map<String, Object> parameterMap = InternalUtils.getParameterMap(request);
//			DataTablesInput<Direccion> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
//			Direccion bean = new Direccion();
//			bean.setActivo((String) parameterMap.get("activo"));
//			Direccion parent = new Direccion();
//			String parentId = (String) parameterMap.get("parentAreaId");
//			if (StringUtils.isNotBlank(parentId)){
//				parent.setId(Long.valueOf(parentId));
//			}
//			bean.setParentDireccion(parent);
//			dataTablesInput.setObject(bean);
//
//			DataTablesOutput<Direccion> dataTablesOutput = direccionIntegration.findDataTables(dataTablesInput);
//			if (dataTablesOutput != null) {
//				map.put("data", castDireccionToDireccionViewList(dataTablesOutput.getData()));
//				map.put("draw", dataTablesOutput.getDraw());
//				map.put("recordsTotal", dataTablesOutput.getRecordsTotal());
//				map.put("recordsFiltered", dataTablesOutput.getRecordsFiltered());
//			} else {
//				map.put("data", new ArrayList<Area>());
//			}
//
//			map.put(Constants.STATUS, Constants.OK);
//			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));
//
//		}catch (Exception e){
//			logger.error(e.getMessage(),e);
//			map.put(Constants.STATUS, Constants.ERROR);
//			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
//		}
//		return map;
//	}
//	public List<DireccionView> castDireccionToDireccionViewList(List<Direccion> list) throws Exception {
//		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
//		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
//		return list.stream().map(bean -> {
//			DireccionView view =(DireccionView)BeanParser.parseObjectToNewClass(bean,DireccionView.class,null);
//			view.setActivoDescripcion(tipoBaseMap.containsKey(view.getActivo())?tipoBaseMap.get(view.getActivo()).getDescripcion():null);
//			view.setActivoType(reglaDetalleMap.containsKey(view.getActivo())?reglaDetalleMap.get(view.getActivo()).getValorcadena():null);
//			DireccionView parentView = (DireccionView) BeanParser.parseObjectToNewClass(bean.getParentDireccion(),DireccionView.class,null);
//			view.setParentDireccion(parentView);
//			return view;
//		}).collect(Collectors.toList());
//	}
}
