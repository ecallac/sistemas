/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Area;
import com.common.Ubigeo;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.UbigeoIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.UbigeoView;
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
@RequestMapping("/"+ UbigeoController.NAME)
public class UbigeoController {
	public static final String NAME="ubigeo";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	UbigeoIntegration ubigeoIntegration;
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
		modelAndView.setViewName(UbigeoController.NAME);
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledUbigeos", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableUbigeos(@RequestParam(value = "parentubigeoId", required = false) Long parentubigeoId) {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Ubigeo> list = ubigeoIntegration.findByParentUbigeoId(parentubigeoId);
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
	
//  @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
//  public @ResponseBody Map<String, Object> getAll() {
//      Map<String, Object> map = new HashMap<String, Object>();
//	  try{
//		  List<Ubigeo> list = ubigeoIntegration.findList();
//		  if (list != null) {
//			  map.put("data", list);
//		  } else {
//			  map.put("data", new ArrayList<Ubigeo>());
//		  }
//		  map.put(Constants.STATUS, Constants.OK);
//	  }catch (Exception e){
//		  logger.error(e.getMessage(),e);
//		  map.put(Constants.STATUS, Constants.ERROR);
//		  map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
//	  }
//      return map;
//  }
	
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid UbigeoView view, BindingResult result, Principal principal) {
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
			Ubigeo bean = (Ubigeo) BeanParser.parseObjectToNewClass(view, Ubigeo.class, null);
			if (StringUtils.isNotBlank(view.getParentUbigeoId())) {
				bean.setParentUbigeo((Ubigeo)BeanParser.parseObjectToNewClass(view.getParentUbigeo(), Ubigeo.class, null));
			}
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			ubigeoIntegration.save(bean);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody UbigeoView view) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Ubigeo ubigeo = ubigeoIntegration.findById(view.getId());
			UbigeoView viewStored = (UbigeoView)BeanParser.parseObjectToNewClass(ubigeo, UbigeoView.class, null);
			viewStored.setParentUbigeo((UbigeoView)BeanParser.parseObjectToNewClass(ubigeo.getParentUbigeo(), UbigeoView.class, null));
			map.put("viewBean", viewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
//	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
//    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody UbigeoView view,Principal principal) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//		try{
//			if (view.getIds()!=null){
//				ubigeoIntegration.save(castViewBeanToBaseList(view,principal));
//			}else {
//
//			}
//			Ubigeo ubigeo = (Ubigeo) BeanParser.parseObjectToNewClass(view, Ubigeo.class, null);
//			ubigeo.setUpdatedBy(principal.getName());
//			ubigeoIntegration.save(ubigeo);
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
//	public List<Ubigeo> castViewBeanToBaseList(UbigeoView view, Principal principal){
//		List<Ubigeo> list = new ArrayList<>();
//		for (int i = 0; i < view.getIds().length; i++) {
//			String id = view.getIds()[i];
//			Ubigeo bean = (Ubigeo) BeanParser.parseObjectToNewClass(view, Ubigeo.class, null);
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
//			Ubigeo bean = ubigeoIntegration.findByNombre(nombre);
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
//			DataTablesInput<Ubigeo> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
//			Ubigeo bean = new Ubigeo();
//			bean.setActivo((String) parameterMap.get("activo"));
//			Ubigeo parent = new Ubigeo();
//			String parentId = (String) parameterMap.get("parentAreaId");
//			if (StringUtils.isNotBlank(parentId)){
//				parent.setId(Long.valueOf(parentId));
//			}
//			bean.setParentUbigeo(parent);
//			dataTablesInput.setObject(bean);
//
//			DataTablesOutput<Ubigeo> dataTablesOutput = ubigeoIntegration.findDataTables(dataTablesInput);
//			if (dataTablesOutput != null) {
//				map.put("data", castUbigeoToUbigeoViewList(dataTablesOutput.getData()));
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
//	public List<UbigeoView> castUbigeoToUbigeoViewList(List<Ubigeo> list) throws Exception {
//		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
//		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
//		return list.stream().map(bean -> {
//			UbigeoView view =(UbigeoView)BeanParser.parseObjectToNewClass(bean,UbigeoView.class,null);
//			view.setActivoDescripcion(tipoBaseMap.containsKey(view.getActivo())?tipoBaseMap.get(view.getActivo()).getDescripcion():null);
//			view.setActivoType(reglaDetalleMap.containsKey(view.getActivo())?reglaDetalleMap.get(view.getActivo()).getValorcadena():null);
//			UbigeoView parentView = (UbigeoView) BeanParser.parseObjectToNewClass(bean.getParentUbigeo(),UbigeoView.class,null);
//			view.setParentUbigeo(parentView);
//			return view;
//		}).collect(Collectors.toList());
//	}
}
