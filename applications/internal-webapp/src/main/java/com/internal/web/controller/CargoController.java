/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Area;
import com.common.Cargo;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.AreaView;
import com.internal.web.view.CargoView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.CargoIntegration;
import com.internal.web.utils.Constants;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author efrain
 *
 */
@Controller
@RequestMapping("/"+ CargoController.NAME)
public class CargoController {
	public static final String NAME="cargo";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	CargoIntegration cargoIntegration;
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
		modelAndView.setViewName(CargoController.NAME);
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledCargos", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableCargos() {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Cargo> list = cargoIntegration.findActivos();
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
		  List<Cargo> list = cargoIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Cargo>());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid CargoView cargoView, BindingResult result, Principal principal) {
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
			Cargo cargo = (Cargo) BeanParser.parseObjectToNewClass(cargoView, Cargo.class, null);
			if (StringUtils.isNotBlank(cargoView.getParentCargoId())) {
				cargo.setParentCargo((Cargo)BeanParser.parseObjectToNewClass(cargoView.getParentCargo(), Cargo.class, null));
			}
			if (cargo.getId()==null) {
				cargo.setCreatedBy(principal.getName());
			} else {
				cargo.setUpdatedBy(principal.getName());
			}
			cargoIntegration.save(cargo);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody CargoView cargoView) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Cargo cargo = cargoIntegration.findById(cargoView.getId());
			CargoView cargoViewStored = (CargoView)BeanParser.parseObjectToNewClass(cargo, CargoView.class, null);
			cargoViewStored.setParentCargo((CargoView)BeanParser.parseObjectToNewClass(cargo.getParentCargo(), CargoView.class, null));
			map.put("viewBean", cargoViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody CargoView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				cargoIntegration.save(castViewBeanToBaseList(view,principal));
			}else {

			}
			Cargo cargo = (Cargo) BeanParser.parseObjectToNewClass(view, Cargo.class, null);
			cargo.setUpdatedBy(principal.getName());
			cargoIntegration.save(cargo);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }
	public List<Cargo> castViewBeanToBaseList(CargoView view, Principal principal){
		List<Cargo> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Cargo bean = (Cargo) BeanParser.parseObjectToNewClass(view, Cargo.class, null);
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
			Cargo bean = cargoIntegration.findByNombre(nombre);
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
			DataTablesInput<Cargo> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Cargo bean = new Cargo();
			bean.setActivo((String) parameterMap.get("activo"));
			Cargo parent = new Cargo();
			String parentId = (String) parameterMap.get("parentAreaId");
			if (StringUtils.isNotBlank(parentId)){
				parent.setId(Long.valueOf(parentId));
			}
			bean.setParentCargo(parent);
			dataTablesInput.setObject(bean);

			DataTablesOutput<Cargo> dataTablesOutput = cargoIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castCargoToCargoViewList(dataTablesOutput.getData()));
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
	public List<CargoView> castCargoToCargoViewList(List<Cargo> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			CargoView view =(CargoView)BeanParser.parseObjectToNewClass(bean,CargoView.class,null);
			view.setActivoDescripcion(tipoBaseMap.containsKey(view.getActivo())?tipoBaseMap.get(view.getActivo()).getDescripcion():null);
			view.setActivoType(reglaDetalleMap.containsKey(view.getActivo())?reglaDetalleMap.get(view.getActivo()).getValorcadena():null);
			CargoView parentView = (CargoView) BeanParser.parseObjectToNewClass(bean.getParentCargo(),CargoView.class,null);
			view.setParentCargo(parentView);
			return view;
		}).collect(Collectors.toList());
	}
}
