/**
 * 
 */
package com.internal.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.common.Persona;
import com.common.Telefono;
import com.common.TipoBase;
import com.internal.web.beans.PasswordView;
import com.internal.web.beans.ProfileView;
import com.internal.web.service.LoginService;
import com.internal.web.service.integration.PersonaIntegration;
import com.internal.web.service.integration.TelefonoIntegration;
import com.internal.web.service.integration.TipoBaseIntegration;
import com.internal.web.service.integration.UserIntegration;
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
    private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value={"","/"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		loginService.addSessionObjects(session,principal);
		modelAndView.setViewName("profile");
		return modelAndView;
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.GET})
    public @ResponseBody  Map<String, Object> load(Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userIntegration.findByUserName(principal.getName());
        TipoBase status = tipoBaseIntegration.findByCodigo(user.getStatus());
        ProfileView view = new ProfileView();
        
        if (user.getStatus().equals("S_ACTIVE")) {
        	view.setUserStatusType(Constants.SUCCESS);
		} else if (user.getStatus().equals("S_INACTIVE")) {
			view.setUserStatusType(Constants.DANGER);
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
        map.put("view", view);
        map.put(Constants.STATUS, Constants.OK);
        return map;
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
		map.put("validated", true);
		
		if (!passwordView.getNewPassword().equals(passwordView.getNewPasswordAgain())) {
			 map.put(Constants.STATUS, Constants.ERROR);
	         map.put(Constants.MESSAGE, "¡La nueva contraseña no es la misma! Por favor, inténtalo de nuevo.");
	         return map;
		}
		
		User user = userIntegration.findById(passwordView.getUserId());
		boolean matches= passwordEncoder.matches(passwordView.getOldPassword(), user.getPassword());
		if (user!=null && !matches) {
			 map.put(Constants.STATUS, Constants.ERROR);
	         map.put(Constants.MESSAGE, "La contraseña actual es incorrecta.");
	         return map;
		}
		
    	
    	
        user.setUpdatedBy(principal.getName());
        user.setPassword(passwordEncoder.encode(passwordView.getOldPassword()));
        userIntegration.savePasswordById(user);
        
        map.put(Constants.STATUS, Constants.OK);
        map.put(Constants.MESSAGE, Constants.SUCCESS_MESSAGE);
        return map;
    }
	
}
