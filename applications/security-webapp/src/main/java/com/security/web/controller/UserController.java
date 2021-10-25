/**
 * 
 */
package com.security.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.BeanParser;
import com.common.Entidad;
import com.common.EntidadRol;
import com.common.Persona;
import com.common.TipoBase;
import com.security.Role;
import com.security.User;
import com.security.web.bean.ModuleView;
import com.security.web.bean.PersonaView;
import com.security.web.bean.RoleView;
import com.security.web.bean.UserEditPasswordView;
import com.security.web.bean.UserEditView;
import com.security.web.bean.UserNewView;
import com.security.web.bean.UserRoleView;
import com.security.web.bean.UserView;
import com.security.web.service.LoginService;
import com.security.web.service.integration.EntidadRolIntegration;
import com.security.web.service.integration.PersonaIntegration;
import com.security.web.service.integration.RoleIntegration;
import com.security.web.service.integration.TipoBaseIntegration;
import com.security.web.service.integration.UserIntegration;
import com.security.web.utils.ExcelUtils;
import com.security.web.utils.SecurityConstants;
import com.security.web.utils.SecurityUtil;

/**
 * @author efrain.calla
 *
 */
@Controller
public class UserController {
	@Autowired
	LoginService loginService;
	@Autowired
	TipoBaseIntegration tipoBaseIntegration;

    @Autowired
    UserIntegration userIntegration;
    
    @Autowired
    RoleIntegration roleIntegration;

    @Autowired
    PersonaIntegration personaIntegration;
    
    @Autowired
    EntidadRolIntegration entidadRolIntegration;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	@Value("${report.web.xls.templates}")
	private String xlsReportTemplate;

    
    @RequestMapping("/reportUserList/{format}")
    public ModelAndView reportUser(ModelMap modelMap, ModelAndView modelAndView,@PathVariable String format){
    	
    	List<User> users = userIntegration.findList();
        modelMap.put("datasource", users);
        modelMap.put("format", format);
        modelAndView = new ModelAndView("rpt_userJasperReport", modelMap);
        return modelAndView;
    }
    
    @RequestMapping("/reportUserListXls")
    public void reportUserXls(HttpServletResponse response)  throws IOException{
		String templateFileName = "userReport.xls";
		String reportTemplate = xlsReportTemplate+templateFileName;
		
    	List<User> users = userIntegration.findList();
    	
    	Map<String, Object> beans = new HashMap<String, Object>();
    	beans.put("fileName", templateFileName);
		beans.put("dateReport", String.valueOf(new Date()));
		
		if(SecurityUtil.containElemnts(users)){
			beans.put("listUsers", users);
			beans.put("dividedInto", users.size());
		}else{
			beans.put("listUsers", new ArrayList<User>());
			beans.put("dividedInto", "");
		}
    	
    	ExcelUtils.createReport(this.getClass(), beans, reportTemplate, templateFileName, response);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(value={"/user"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		TipoBase tipoBase = tipoBaseIntegration.findByCodigo(SecurityConstants.TIPOBASE_CODIGO_PERSONA);
		session.setAttribute("tipoEntidadId", tipoBase.getId());
		loginService.addSessionObjects(session,principal);
		modelAndView.setViewName("user");
		return modelAndView;
	}
    
    @RequestMapping(value = "/user/userStatusType", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> userStatusType() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    List<TipoBase> list = tipoBaseIntegration.findByCategoriaActivos(SecurityConstants.TIPOBASE_CATEGORIA_USER_STATUS);
	    if (list != null) {
		    map.put("data", list);
		} else {
			map.put("data", new ArrayList<TipoBase>());
	   	}
	    return map;
	}
    @RequestMapping(value = "/user/personaDocumentoType", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> personaDocumentoType() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    List<TipoBase> list = tipoBaseIntegration.findByCategoriaActivos(SecurityConstants.TIPOBASE_CATEGORIA_TYPE_PERSONA_DOCUMENTO);
	    if (list != null) {
		    map.put("data", list);
		} else {
			map.put("data", new ArrayList<TipoBase>());
	   	}
	    return map;
	}
    @RequestMapping(value = "/user/personaEstadoCivilType", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> personaEstadoCivilType() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    List<TipoBase> list = tipoBaseIntegration.findByCategoriaActivos(SecurityConstants.TIPOBASE_CATEGORIA_TYPE_PERSONA_ESTADO_CIVIL);
	    if (list != null) {
		    map.put("data", list);
		} else {
			map.put("data", new ArrayList<TipoBase>());
	   	}
	    return map;
	}
    
    @RequestMapping(value = "/user/personaPorTermino", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<Persona> getPersonaPorTermino(@RequestParam(value = "termino", required = true) String termino){
    	List<Persona> personas = personaIntegration.findByTermino(termino);
    	for (Persona persona : personas) {
    		persona.setFullName(persona.getNumeroidentificacion()+ " - " +persona.getNombres()+ " " + persona.getApellidos());
		}
    	return personas;
    }
    
    @RequestMapping(value = "/user/list", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserView> list = castUserToUserEditViewList(userIntegration.findList());
        if (list != null) {
        	List<TipoBase> tipoBases = tipoBaseIntegration.findByCategoriaActivos(SecurityConstants.TIPOBASE_CATEGORIA_USER_STATUS);
        	for (UserView userView : list) {
        		for (TipoBase tipoBase : tipoBases) {
            		if (userView.getStatus().equals(tipoBase.getId().toString())) {
    					userView.setStatus(tipoBase.getCodigo());
    				}
    			}
        		try {
        			Persona persona = personaIntegration.findByEntidadRolId(userView.getEntidadRoleId());
            		userView.setEntityName(persona.getNombres()+ " " + persona.getApellidos());
				} catch (Exception e) {
					e.printStackTrace();
				}
        		
			}
            map.put("data", list);
        } else {
      	  map.put("data", new ArrayList<ModuleView>());
        }
        return map;
    }
    
    public List<UserView> castUserToUserEditViewList(List<User> users){
		List<UserView> userEditViews = new ArrayList<UserView>();
		for (User user : users) {
			UserView userView = (UserView)BeanParser.parseObjectToNewClass(user, UserView.class, null);
			userEditViews.add(userView);
		}
		return userEditViews;
	}
    
//    @RequestMapping(value={"/user/delete"}, method={RequestMethod.POST})
//	public @ResponseBody Map<String, Object> delete(@RequestBody UserView userView){
//		Map<String, Object> map = new HashMap<String, Object>();
//		User user = userIntegration.findById(Long.valueOf(userView.getId()));
//		userIntegration.delete(user);
//        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        return map;
//	}
    
    @RequestMapping(value = "/user/loadEdit", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> loadEdit(@RequestBody UserView userView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userIntegration.findById(userView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserEditView)BeanParser.parseObjectToNewClass(user, UserEditView.class, null));
        return map;
    }
    
    @RequestMapping(value = "/user/loadEditPassword", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> loadEditPassword(@RequestBody UserView userView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userIntegration.findById(userView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserEditPasswordView)BeanParser.parseObjectToNewClass(user, UserEditPasswordView.class, null));
        return map;
    }
    
    @RequestMapping(value = "/user/loadEditUserRole", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> loadEditUserRole(@RequestBody UserView userView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userIntegration.findById(userView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserView)BeanParser.parseObjectToNewClass(user, UserView.class, null));
        Persona persona = personaIntegration.findByEntidadRolId(user.getEntidadRoleId());
        persona.setFullName(persona.getNumeroidentificacion()+ " - " +persona.getNombres()+ " " + persona.getApellidos());
        map.put("persona", persona);
        return map;
    }
    
    @RequestMapping(value = "/user/enabledRolesByUser", method = {RequestMethod.GET,RequestMethod.POST})
	  public @ResponseBody Map<String, Object> initializeEnabledRolesByUser(@RequestBody UserView userView) {
	      Map<String, Object> map = new HashMap<String, Object>();
	      User user = userIntegration.findById(userView.getId());
		  List<Role> roles = roleIntegration.findEnabledList();
		  List<UserRoleView> list = castUserRoleViewList(userView.getId(), roles, user.getRoles());
		  if (list != null) {
		      map.put("data", list);
		  } else {
			  map.put("data", new ArrayList<RoleView>());
		      }
		      return map;
		  }
    private List<UserRoleView> castUserRoleViewList(Long userId,List<Role> enabledRoles,List<Role> userRoles){
    	List<UserRoleView> userRoleViews = new ArrayList<>();
    	for (Role role : enabledRoles) {
    		UserRoleView userRoleView = new UserRoleView();
			userRoleView.setUserId(userId);
			userRoleView.setRoleId(role.getId());
			userRoleView.setRoleDescription(role.getDescription());
			for (Role userRole : userRoles) {
				if (userRole.getId().toString().equals(role.getId().toString())) {
					userRoleView.setSelected(SecurityConstants.YES);
				}
			}
			userRoleViews.add(userRoleView);
		}
    	
		return userRoleViews;
    }
    
    @RequestMapping(value = "/user/assignRolesbyUser", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> assignRolesbyUser(@RequestBody UserRoleView userRoleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userRoleView.getSelected().equals(SecurityConstants.YES)) {
        	userIntegration.addRoleUserAssociation(userRoleView.getUserId(), userRoleView.getRoleId());
		} else {
			userIntegration.removeRoleUserAssociation(userRoleView.getUserId(), userRoleView.getRoleId());
		}
    	
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
        return map;
    }
    
    @RequestMapping(value = "/user/saveNewPerson", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> saveNewPerson(@RequestBody @Valid PersonaView personaView,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	    }
		map.put("validated", true);
		
		Persona persona = (Persona) BeanParser.parseObjectToNewClass(personaView, Persona.class, null);
		try {
			persona.setFechanacimiento(SecurityUtil.stringToTimestamp(personaView.getFechanacimiento(), PersonaView.FORMAT));
		} catch (Exception e) {
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, e.getMessage());
		}
		
		Entidad entidad = new Entidad();
		entidad.setCreatedBy(principal.getName());
		entidad.setTipoEntidad(personaView.getTipoEntidadId());
		persona.setEntidad(entidad);
		persona.setCreatedBy(principal.getName());
		persona = personaIntegration.save(persona);
		persona.setFullName(persona.getNumeroidentificacion()+ " - " +persona.getNombres()+ " " + persona.getApellidos());
		map.put("viewBean", persona);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
    
    
    @RequestMapping(value = "/user/saveNew", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> saveNew(@RequestBody @Valid UserNewView userNewView,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	    }
		map.put("validated", true);
		
		if (userNewView.getEntidadId().equals("")) {
			 map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put(SecurityConstants.MESSAGE, "Person is not registered in the system. Register and try again please.");
	         return map;
		}
		map.putAll(verifyUserName(userNewView.getUserName()));
		if (SecurityConstants.ERROR.equals((String) map.get(SecurityConstants.STATUS))) {
			map.put("validated", false);
			Map<String, String> errors = new HashMap<String, String>();
			errors.put("userName", (String) map.get(SecurityConstants.MESSAGE));
			map.put("messages", errors);
	         return map;
		}
		if (!userNewView.getPassword().equals(userNewView.getPasswordAgain())) {
			 map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put(SecurityConstants.MESSAGE, "The new password aren't same! Try again please.");
	         return map;
		}
		
		User user = (User) BeanParser.parseObjectToNewClass(userNewView, User.class, null);
		TipoBase tipoBase = tipoBaseIntegration.findByCodigo(SecurityConstants.TIPOBASE_CODIGO_ER_USUARIO);
		EntidadRol entidadRoleBean = new EntidadRol();
		Entidad entidadBean = new Entidad();
		entidadBean.setId(Long.parseLong(userNewView.getEntidadId()));
		entidadRoleBean.setEntidad(entidadBean);
		entidadRoleBean.setCreatedBy(principal.getName());
		entidadRoleBean.setTipoEntidadrol(tipoBase.getId().toString());
		try {
			EntidadRol entidadRoleBeanRes = entidadRolIntegration.save(entidadRoleBean);
			if (entidadRoleBeanRes!=null) {
				user.setEntidadRoleId(entidadRoleBeanRes.getId());
			}else {
				map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
		        map.put(SecurityConstants.MESSAGE, "We Can't Create EntidadRol");
		        return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, e.getMessage());
	        return map;
		}
		
		user.setCreatedBy(principal.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userIntegration.save(user);
        
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
    
    @RequestMapping(value = "/user/saveEdit", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> saveEdit(@RequestBody @Valid UserEditView userEditView,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	      }
        
		User user = (User) BeanParser.parseObjectToNewClass(userEditView, User.class, null);
		user.setUpdatedBy(principal.getName());
		userIntegration.save(user);
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
    
    @RequestMapping(value = "/user/saveEditPassword", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> saveEditPassword(@RequestBody @Valid UserEditPasswordView userEditPasswordView,BindingResult result,Principal principal) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	      }
        
		if (!userEditPasswordView.getPassword().equals(userEditPasswordView.getPasswordAgain())) {
			map.put("validated", true);
			 map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put(SecurityConstants.MESSAGE, "The new password aren't same! Try again please.");
	         return map;
		}
    	
    	User user = (User)BeanParser.parseObjectToNewClass(userEditPasswordView, User.class, null);
        user.setUpdatedBy(principal.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userIntegration.savePasswordById(user);
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
    
    @RequestMapping(value = "/user/verifyUserName", method = {RequestMethod.GET})
    public @ResponseBody  Map<String, Object> verifyUserName(@RequestParam(value = "userName", required = true) String userName) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        User user = userIntegration.findByUserName(userName);
        if (user==null) {
        	map.put(SecurityConstants.STATUS, SecurityConstants.OK);
		} else {
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, "UserName already exists.");
		}
        return map;
    }
}
