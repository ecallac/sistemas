/**
 * 
 */
package com.internal.web.controller;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.Utils;
import com.common.*;
import com.internal.web.view.DireccionView;
import com.internal.web.service.integration.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.BeanParser;
import com.internal.web.view.PasswordView;
import com.internal.web.view.ProfileView;
import com.internal.web.service.LoginService;
import com.internal.web.utils.Constants;
import com.security.User;

/**
 * @author efrain.calla
 *
 */
@Controller
@RequestMapping("/"+ProfileController.NAME)
public class ProfileController {
	public static final String NAME="profile";
    private Logger logger = Logger.getLogger(this.getClass());
    
	@Autowired
	LoginService loginService;
	
	@Autowired
    UserIntegration userIntegration;
	
	@Autowired
    PersonaIntegration personaIntegration;
	
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;
	
	@Autowired
	TelefonoIntegration telefonoIntegration;

	@Autowired
	DireccionIntegration direccionIntegration;

	@Autowired
	ReglaDetalleIntegration reglaDetalleIntegration;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		try{
			loginService.addSessionObjects(session,principal);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
		}
		modelAndView.setViewName("profile");
		return modelAndView;
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.GET})
    public @ResponseBody  Map<String, Object> load(Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();
		try{
			User user = userIntegration.findByUserName(principal.getName());
			TipoBase status = tipoBaseIntegration.findByCodigo(user.getStatus());
			ProfileView view = new ProfileView();

			Map<String,ReglaDetalle> reglaDetallesMap = reglaDetalleIntegration.getReglasMap(reglaDetalleIntegration.findByCodigo(Constants.RULE_USER_STATUS_LABEL_TYPE));

			if (reglaDetallesMap.containsKey(user.getStatus())){
				ReglaDetalle regla = reglaDetallesMap.get(user.getStatus());
				view.setUserStatusType(regla.getValorcadena());
			}else{
				view.setUserStatusType("secondary");
			}

			user.setStatus(status.getDescripcion());
			map.put("user", user);
			Persona persona = personaIntegration.findByEntidadRolId(user.getEntidadRoleId());
			persona.setFullName(persona.getNombres()+ " " + persona.getApellidos());
			persona.setTipoDocumentoIdentificaion(tipoBaseIntegration.findByCodigo(persona.getTipoDocumentoIdentificaion()).getDescripcion());
			persona.setTipoEstadoCivil(tipoBaseIntegration.findByCodigo(persona.getTipoEstadoCivil()).getDescripcion());
			map.put("persona", persona);
			List<Telefono> telefonos = telefonoIntegration.findByEntidadId(persona.getEntidad().getId());
			for (Telefono telefono:telefonos) {
				telefono.setTipo(tipoBaseIntegration.findByCodigo(telefono.getTipo()).getDescripcion());
			}
			map.put("telefonos", telefonos);
			List<DireccionView> direcciones = setViewFields(direccionIntegration.findByEntidadId(persona.getEntidad().getId()));
			map.put("direcciones", direcciones);
			map.put("view", view);
			map.put(Constants.STATUS, Constants.OK);

		}catch (Exception e){
			logger.error(e.getMessage(),e);
			map.put(Constants.STATUS, Constants.ERROR);
			map.put(Constants.MESSAGE, Utils.getErrorMessage(Constants.ERROR_MESSAGE_GET,e.getMessage()));
		}
        return map;
    }

	public List<DireccionView> setViewFields(List<Direccion> direcciones){
		List<DireccionView> direccions = new ArrayList<DireccionView>();
		if (CollectionUtils.isNotEmpty(direcciones)){
			for (Direccion direccion:direcciones) {
				DireccionView view = (DireccionView) BeanParser.parseBetweenObjects(direccion, new DireccionView());
				if ("Y".equals(direccion.getEsprincipal())){
					view.setEsprincipal("Principal");
					view.setEsprincipalStyle("success");
				}else{
					view.setEsprincipal("");
					view.setEsprincipalStyle("");
				}
				view.setUbicaionTotal(getUbicacionTotal("",direccion.getUbigeo()));
				direccions.add(view);
			}
		}
		return direccions;
	}
	public String getUbicacionTotal(String ubicacion,Ubigeo ubigeo){
		String utotal ="";
		if (ubigeo!=null){
			utotal = ubicacion+ubigeo.getDescripcion();
			return getUbicacionTotal(utotal+" ",ubigeo.getParentUbigeo());
		}
		return ubicacion;
	}
	@RequestMapping(value = "/saveEditPassword", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> saveEditPassword(@RequestBody @Valid PasswordView passwordView,BindingResult result,Principal principal) {
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
			if (!passwordView.getNewPassword().equals(passwordView.getNewPasswordAgain())) {
				map.put(Constants.STATUS, Constants.ERROR);
				map.put(Constants.MESSAGE, "�La nueva contrase�a no es la misma! Por favor, int�ntalo de nuevo.");
				return map;
			}

			User user = userIntegration.findById(passwordView.getUserId());
			boolean matches= passwordEncoder.matches(passwordView.getOldPassword(), user.getPassword());
			if (user!=null && !matches) {
				map.put(Constants.STATUS, Constants.ERROR);
				map.put(Constants.MESSAGE, "La contrase�a actual es incorrecta.");
				return map;
			}

			user.setUpdatedBy(principal.getName());
			user.setPassword(passwordEncoder.encode(passwordView.getOldPassword()));
			userIntegration.savePasswordById(user);

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
	
}
