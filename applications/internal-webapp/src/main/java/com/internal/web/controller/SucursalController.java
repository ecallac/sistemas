/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.*;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.SucursalIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.DireccionView;
import com.internal.web.view.OrganizacionView;
import com.internal.web.view.SucursalView;
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
@RequestMapping("/"+ SucursalController.NAME)
public class SucursalController {
	public static final String NAME="sucursal";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	SucursalIntegration sucursalIntegration;
	@Autowired
	ReglaDetalleIntegration reglaDetalleIntegration;
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal,@RequestParam(value = "organizacionId",required = true) Long organizacionId){
		ModelAndView modelAndView = new ModelAndView();
		session.setAttribute("orgId",organizacionId);
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName(SucursalController.NAME);
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledSucursals", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableSucursals(@RequestParam(value = "parentId",required = true) Long parentId) {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Sucursal> list = sucursalIntegration.findActivos(parentId);
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
		  List<Sucursal> list = sucursalIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Sucursal>());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid SucursalView view, BindingResult result, Principal principal) {
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
			Sucursal bean = (Sucursal) BeanParser.parseObjectToNewClass(view, Sucursal.class, null);
//			if (StringUtils.isNotBlank(sucursalView.getParentSucursalId())) {
//				bean.setParentSucursal((Sucursal)BeanParser.parseObjectToNewClass(sucursalView.getParentSucursal(), Sucursal.class, null));
//			}
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			Organizacion organizacion = new Organizacion();
			organizacion.setId(Long.valueOf(view.getOrganizacionId()));
			bean.setOrganizacion(organizacion);
			Direccion direccion = new Direccion();
			direccion.setId(Long.valueOf(view.getDireccionId()));
			bean.setDireccion(direccion);
			sucursalIntegration.save(bean);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody SucursalView sucursalView) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Sucursal bean = sucursalIntegration.findById(sucursalView.getId());
			SucursalView sucursalViewStored = (SucursalView)BeanParser.parseObjectToNewClass(bean, SucursalView.class, null);
//			sucursalViewStored.setParentSucursal((SucursalView)BeanParser.parseObjectToNewClass(bean.getParentSucursal(), SucursalView.class, null));
			sucursalViewStored.setOrganizacionId(bean.getOrganizacion().getId().toString());
			sucursalViewStored.setDireccionId(bean.getDireccion().getId().toString());
			map.put("viewBean", sucursalViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody SucursalView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				sucursalIntegration.save(castViewBeanToBaseList(view,principal));
			}else {

			}
			Sucursal bean = (Sucursal) BeanParser.parseObjectToNewClass(view, Sucursal.class, null);
			bean.setUpdatedBy(principal.getName());
			sucursalIntegration.save(bean);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }
	public List<Sucursal> castViewBeanToBaseList(SucursalView view, Principal principal){
		List<Sucursal> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Sucursal bean = (Sucursal) BeanParser.parseObjectToNewClass(view, Sucursal.class, null);
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
			Sucursal bean = sucursalIntegration.findByNombre(nombre);
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
			DataTablesInput<Sucursal> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Sucursal bean = new Sucursal();
			bean.setEstado((String) parameterMap.get("estado"));
//			Sucursal parent = new Sucursal();
//			String parentId = (String) parameterMap.get("parentAreaId");
//			if (StringUtils.isNotBlank(parentId)){
//				parent.setId(Long.valueOf(parentId));
//			}
//			bean.setParentSucursal(parent);
			dataTablesInput.setObject(bean);

			DataTablesOutput<Sucursal> dataTablesOutput = sucursalIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castSucursalToSucursalViewList(dataTablesOutput.getData()));
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
	public List<SucursalView> castSucursalToSucursalViewList(List<Sucursal> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			SucursalView view =(SucursalView)BeanParser.parseObjectToNewClass(bean,SucursalView.class,null);
			view.setEstadoDescripcion(tipoBaseMap.containsKey(view.getEstado())?tipoBaseMap.get(view.getEstado()).getDescripcion():null);
			view.setEstadoType(reglaDetalleMap.containsKey(view.getEstado())?reglaDetalleMap.get(view.getEstado()).getValorcadena():null);
			view.setTiposucursal(tipoBaseMap.containsKey(view.getTiposucursal())?tipoBaseMap.get(view.getTiposucursal()).getDescripcion():null);
			OrganizacionView parentView = (OrganizacionView) BeanParser.parseObjectToNewClass(bean.getOrganizacion(),OrganizacionView.class,null);
			view.setOrganizacion(parentView);
			DireccionView direccionView = (DireccionView) BeanParser.parseObjectToNewClass(bean.getDireccion(),DireccionView.class,null);
			direccionView.setUbicaionTotal(bean.getDireccion().getDireccionexacta()+ " " +bean.getDireccion().getUbigeo().getDescripcion()+ " " +bean.getDireccion().getUbigeo().getParentUbigeo().getDescripcion()+ " " +bean.getDireccion().getUbigeo().getParentUbigeo().getParentUbigeo().getDescripcion()+ " " +bean.getDireccion().getUbigeo().getParentUbigeo().getParentUbigeo().getParentUbigeo().getDescripcion());
			view.setDireccion(direccionView);
			return view;
		}).collect(Collectors.toList());
	}
}
