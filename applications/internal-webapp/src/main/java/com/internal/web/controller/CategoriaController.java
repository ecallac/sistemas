/**
 * 
 */
package com.internal.web.controller;

import com.*;
import com.common.Area;
import com.common.Categoria;
import com.common.ReglaDetalle;
import com.common.TipoBase;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.CategoriaIntegration;
import com.internal.web.service.integration.ReglaDetalleIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
import com.internal.web.utils.InternalUtils;
import com.internal.web.view.CategoriaView;
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
@RequestMapping("/"+ CategoriaController.NAME)
public class CategoriaController {
	public static final String NAME="categoria";
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	CategoriaIntegration categoriaIntegration;
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
		modelAndView.setViewName(CategoriaController.NAME);
		return modelAndView;
	}
	
	@RequestMapping(value = "/enabledCategorias", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeEnableCategorias() {
		Map<String, Object> map = new HashMap<>();
		try{
			List<Categoria> list = categoriaIntegration.findActivos();
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
		  List<Categoria> list = categoriaIntegration.findList();
		  if (list != null) {
			  map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<Categoria>());
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid CategoriaView view, BindingResult result, Principal principal) {
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
			Categoria bean = (Categoria) BeanParser.parseObjectToNewClass(view, Categoria.class, null);
			if (StringUtils.isNotBlank(view.getCategoriapadreId())) {
				bean.setCategoriapadre((Categoria)BeanParser.parseObjectToNewClass(view.getCategoriapadre(), Categoria.class, null));
			}
			if (bean.getId()==null) {
				bean.setCreatedBy(principal.getName());
			} else {
				bean.setUpdatedBy(principal.getName());
			}
			categoriaIntegration.save(bean);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody CategoriaView view) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Categoria bean = categoriaIntegration.findById(view.getId());
			CategoriaView viewStored = (CategoriaView)BeanParser.parseObjectToNewClass(bean, CategoriaView.class, null);
			viewStored.setCategoriapadre((CategoriaView)BeanParser.parseObjectToNewClass(bean.getCategoriapadre(), CategoriaView.class, null));
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
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody CategoriaView view,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			if (view.getIds()!=null){
				categoriaIntegration.save(castViewBeanToBaseList(view,principal));
			}else {

			}
			Categoria bean = (Categoria) BeanParser.parseObjectToNewClass(view, Categoria.class, null);
			bean.setUpdatedBy(principal.getName());
			categoriaIntegration.save(bean);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }
	public List<Categoria> castViewBeanToBaseList(CategoriaView view, Principal principal){
		List<Categoria> list = new ArrayList<>();
		for (int i = 0; i < view.getIds().length; i++) {
			String id = view.getIds()[i];
			Categoria bean = (Categoria) BeanParser.parseObjectToNewClass(view, Categoria.class, null);
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
			Categoria bean = categoriaIntegration.findByNombre(nombre);
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
			DataTablesInput<Categoria> dataTablesInput = InternalUtils.createDataTablesInput(parameterMap);
			Categoria bean = new Categoria();
			bean.setStatus((String) parameterMap.get("status"));
			Categoria parent = new Categoria();
			String parentId = (String) parameterMap.get("categoriapadreId");
			if (StringUtils.isNotBlank(parentId)){
				parent.setId(Long.valueOf(parentId));
			}
			bean.setCategoriapadre(parent);
			dataTablesInput.setObject(bean);

			DataTablesOutput<Categoria> dataTablesOutput = categoriaIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				map.put("data", castCategoriaToCategoriaViewList(dataTablesOutput.getData()));
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
	public List<CategoriaView> castCategoriaToCategoriaViewList(List<Categoria> list) throws Exception {
		Map<String, ReglaDetalle> reglaDetalleMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(GeneralConstant.SWITH_LABEL_TYPE.getCode()));
		Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
		return list.stream().map(bean -> {
			CategoriaView view =(CategoriaView)BeanParser.parseObjectToNewClass(bean,CategoriaView.class,null);
			view.setStatusDescripcion(tipoBaseMap.containsKey(view.getStatus())?tipoBaseMap.get(view.getStatus()).getDescripcion():null);
			view.setTipocategoria(tipoBaseMap.containsKey(view.getTipocategoria())?tipoBaseMap.get(view.getTipocategoria()).getDescripcion():null);
			view.setTipoestrategiaretiro(tipoBaseMap.containsKey(view.getTipoestrategiaretiro())?tipoBaseMap.get(view.getTipoestrategiaretiro()).getDescripcion():null);
			view.setStatusType(reglaDetalleMap.containsKey(view.getStatus())?reglaDetalleMap.get(view.getStatus()).getValorcadena():null);
			CategoriaView parentView = (CategoriaView) BeanParser.parseObjectToNewClass(bean.getCategoriapadre(),CategoriaView.class,null);
			view.setCategoriapadre(parentView);
			return view;
		}).collect(Collectors.toList());
	}
}
