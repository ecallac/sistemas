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

import com.common.client.bean.EntidadRoleBean;
import com.common.client.bean.PersonaBean;
import com.common.client.bean.TipoBaseBean;
import com.security.domain.Role;
import com.security.domain.User;
import com.security.service.RoleService;
import com.security.service.UserService;
import com.security.utils.BeanParser;
import com.security.utils.SecurityConstants;
import com.security.utils.SecurityUtil;
import com.security.web.bean.ModuleView;
import com.security.web.bean.RoleView;
import com.security.web.bean.UserEditPasswordView;
import com.security.web.bean.UserEditView;
import com.security.web.bean.UserNewView;
import com.security.web.bean.UserRoleView;
import com.security.web.bean.UserView;
import com.security.web.service.integration.CommonServiceIntegration;
import com.security.web.utils.ExcelUtils;

/**
 * @author efrain.calla
 *
 */
@Controller
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	RoleService roleService;
     
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	@Value("${report.web.xls.templates}")
	private String xlsReportTemplate;

    @Autowired
    CommonServiceIntegration commonServiceIntegration;
    
    @RequestMapping("/reportUserList/{format}")
    public ModelAndView reportUser(ModelMap modelMap, ModelAndView modelAndView,@PathVariable String format){
    	
    	List<User> users = userService.findAllUsers();
        modelMap.put("datasource", users);
        modelMap.put("format", format);
        modelAndView = new ModelAndView("rpt_userJasperReport", modelMap);
        return modelAndView;
    }
    
    @RequestMapping("/reportUserListXls")
    public void reportUserXls(HttpServletResponse response)  throws IOException{
		String templateFileName = "userReport.xls";
		String reportTemplate = xlsReportTemplate+templateFileName;
		
    	List<User> users = userService.findAllUsers();
    	
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
	public ModelAndView list(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		session.setAttribute("URL_USER_STATUS_LIST", commonServiceIntegration.getTipoBaseByCategory()+"?categoria="+SecurityConstants.TIPOBASE_CATEGORIA_USER_STATUS);
		session.setAttribute("URL_TYPE_DOCUMENTO_LIST", commonServiceIntegration.getTipoBaseByCategory()+"?categoria="+SecurityConstants.TIPOBASE_CATEGORIA_TYPE_PERSONA_DOCUMENTO);
		session.setAttribute("URL_TYPE_ESTADO_CIVIL_LIST", commonServiceIntegration.getTipoBaseByCategory()+"?categoria="+SecurityConstants.TIPOBASE_CATEGORIA_TYPE_PERSONA_ESTADO_CIVIL);
		session.setAttribute("URL_PERSONA_LIST", commonServiceIntegration.getPersonaPorTermino()+"?termino=");
		return modelAndView;
	}
    
    @RequestMapping(value = "/user/list", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserView> list = castUserToUserEditViewList(userService.findAllUsers());
        if (list != null) {
        	List<TipoBaseBean> tipoBaseBeans = commonServiceIntegration.getTipoBasesXCategoriasActivas(SecurityConstants.TIPOBASE_CATEGORIA_USER_STATUS);
        	for (UserView userView : list) {
        		for (TipoBaseBean tipoBaseBean : tipoBaseBeans) {
            		if (userView.getStatus().equals(tipoBaseBean.getId().toString())) {
    					userView.setStatus(tipoBaseBean.getCodigo());
    				}
    			}
        		
        		try {
        			PersonaBean personaBean = commonServiceIntegration.getPersonaPorEntidadRolId(userView.getEntidadRoleId());
            		userView.setEntityName(personaBean.getFullName());
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
    
    @RequestMapping(value={"/user/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody UserView userView){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.findUserById(Long.valueOf(userView.getId()));
		userService.delete(user);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
    
    @RequestMapping(value = "/user/loadEdit", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> loadEdit(@RequestBody UserView userView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findUserById(userView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserEditView)BeanParser.parseObjectToNewClass(user, UserEditView.class, null));
        return map;
    }
    
    @RequestMapping(value = "/user/loadEditPassword", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> loadEditPassword(@RequestBody UserView userView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findUserById(userView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserEditPasswordView)BeanParser.parseObjectToNewClass(user, UserEditPasswordView.class, null));
        return map;
    }
    
    @RequestMapping(value = "/user/loadEditUserRole", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> loadEditUserRole(@RequestBody UserView userView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findUserById(userView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserView)BeanParser.parseObjectToNewClass(user, UserView.class, null));
        return map;
    }
    
    @RequestMapping(value = "/user/enabledRolesByUser", method = {RequestMethod.GET,RequestMethod.POST})
	  public @ResponseBody Map<String, Object> initializeEnabledRolesByUser(@RequestBody UserView userView) {
	      Map<String, Object> map = new HashMap<String, Object>();
	      User user = userService.findUserById(userView.getId());
	  List<Role> roles = roleService.findByEnabled(SecurityConstants.YES);
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
        	userService.saveRoleInUser(userRoleView.getUserId(), userRoleView.getRoleId());
		} else {
			userService.deleteRoleFromUser(userRoleView.getUserId(), userRoleView.getRoleId());
		}
    	
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
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
		Map<String, Object> userNameValidation = verifyUserName(userNewView.getUserName());
		if (SecurityConstants.ERROR.equals((String) userNameValidation.get(SecurityConstants.STATUS))) {
	         return userNameValidation;
		}
		if (!userNewView.getPassword().equals(userNewView.getPasswordAgain())) {
			 map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	         map.put(SecurityConstants.MESSAGE, "The new password aren't same! Try again please.");
	         return map;
		}
		
		User user = (User) BeanParser.parseObjectToNewClass(userNewView, User.class, null);
		TipoBaseBean tipoBaseBean = commonServiceIntegration.getTipoBasesXCodigo(SecurityConstants.TIPOBASE_CODIGO_ER_USUARIO);
		EntidadRoleBean entidadRoleBean = new EntidadRoleBean();
		entidadRoleBean.setEntidadId(Long.parseLong(userNewView.getEntidadId()));
		entidadRoleBean.setCreatedBy(principal.getName());
		entidadRoleBean.setTipoEntidadRole(tipoBaseBean.getId().toString());
		try {
			EntidadRoleBean entidadRoleBeanRes = commonServiceIntegration.saveEntidadRol(entidadRoleBean);
			if (entidadRoleBeanRes!=null) {
				user.setEntidadRoleId(entidadRoleBeanRes.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, e.getMessage());
	        return map;
		}
		
		user.setCreatedBy(principal.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
        
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
		userService.save(user);
        
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
    	userService.savePasswordById(user);
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
    
    @RequestMapping(value = "/user/verifyUserName", method = {RequestMethod.GET})
    public @ResponseBody  Map<String, Object> verifyUserName(@RequestParam(value = "userName", required = true) String userName) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        User user = userService.findUserByUserName(userName);
        if (user==null) {
        	map.put(SecurityConstants.STATUS, SecurityConstants.OK);
		} else {
			map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
	        map.put(SecurityConstants.MESSAGE, "This value already exists.");
		}
        return map;
    }
}
