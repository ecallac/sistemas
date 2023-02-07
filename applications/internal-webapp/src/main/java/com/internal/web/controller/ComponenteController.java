/**
 * 
 */
package com.internal.web.controller;

import com.BeanParser;
import com.DataTablesInput;
import com.DataTablesOutput;
import com.Utils;
import com.common.Componente;
import com.common.TipoBase;
import com.internal.web.beans.ComponenteView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.ComponenteIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.utils.Constants;
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
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid ComponenteView componenteView, BindingResult result, Principal principal) {
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

			Componente componente = (Componente) BeanParser.parseObjectToNewClass(componenteView, Componente.class, null);
			if (componente.getId()==null) {
				componente.setCreatedBy(principal.getName());
			} else {
				componente.setUpdatedBy(principal.getName());
			}
			componenteIntegration.save(componente);

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
    public @ResponseBody  Map<String, Object> load(@RequestBody ComponenteView componenteView) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Componente componente = componenteIntegration.findById(componenteView.getId());
			ComponenteView componenteViewStored = (ComponenteView)BeanParser.parseObjectToNewClass(componente, ComponenteView.class, null);
			map.put("componenteView", componenteViewStored);
			map.put(Constants.STATUS, Constants.OK);
		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }
	
	@RequestMapping(value = "/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody ComponenteView componenteView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();

		try{
			Componente componente = (Componente) BeanParser.parseObjectToNewClass(componenteView, Componente.class, null);
			componente.setUpdatedBy(principal.getName());
			componenteIntegration.save(componente);
			map.put(Constants.STATUS, Constants.OK);
			map.put(Constants.MESSAGE, Utils.getSuccessMessage(Constants.SUCCESS_MESSAGE));

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE,e.getMessage()));
		}
        return map;
    }

	@RequestMapping(value = "/findByPage", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody  Map<String, Object> findByPage(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Map<String, Object> parameterMap = getParameterMap(request);
			DataTablesInput<Componente> dataTablesInput = createDataTablesInput(parameterMap);
			Componente componente = new Componente();
			componente.setStatus((String) parameterMap.get("status"));
			dataTablesInput.setObject(componente);

			DataTablesOutput<Componente> dataTablesOutput = componenteIntegration.findDataTables(dataTablesInput);
			if (dataTablesOutput != null) {
				List<Componente> componentes = dataTablesOutput.getData();
				Map<String, TipoBase> tipoBaseMap = tipoBaseIntegration.findAllMap();
				for (Componente  componenteL : componentes){
					if (tipoBaseMap.containsKey(componenteL.getTipoComponnte())){
						TipoBase tipoBase = tipoBaseMap.get(componenteL.getTipoComponnte());
						componenteL.setTipoComponnte(tipoBase.getDescripcion());
					}
				}
				map.put("data", componentes);
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

	public Map<String, Object> getParameterMap(HttpServletRequest request){
		Enumeration enumeration = request.getParameterNames();
		Map<String, Object> parameterMap = new HashMap<>();
		while(enumeration.hasMoreElements()){
			String parameterName = (String) enumeration.nextElement();
			parameterMap.put(parameterName, request.getParameter(parameterName));
		}
		return parameterMap;
	}

	public DataTablesInput createDataTablesInput(Map<String, Object> parameterMap){
		DataTablesInput dataTablesInput = new DataTablesInput();
		dataTablesInput.setDraw((String) parameterMap.get("draw"));
		dataTablesInput.setStart((String) parameterMap.get("start"));
		dataTablesInput.setRowPerPage((String) parameterMap.get("length"));
		dataTablesInput.setColumnSortOrderDirection((String) parameterMap.get("order[0][dir]"));
		dataTablesInput.setSearchValue((String) parameterMap.get("search[value]"));
		dataTablesInput.setColumnName((String) parameterMap.get("columns["+(String) parameterMap.get("order[0][column]")+"][data]"));
		return dataTablesInput;
	}
}
