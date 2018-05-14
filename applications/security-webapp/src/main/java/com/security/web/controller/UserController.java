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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.utils.BeanParser;
import com.common.utils.CommonConstants;
import com.common.utils.CommonUtil;
import com.security.domain.BusinessException;
import com.security.domain.Module;
import com.security.domain.User;
import com.security.service.UserService;
import com.security.utils.SecurityConstants;
import com.security.web.bean.ModuleView;
import com.security.web.bean.ResetUserView;
import com.security.web.bean.UserEditView;
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
    private PasswordEncoder passwordEncoder;
    
    @Autowired
	@Value("${report.web.xls.templates}")
	private String xlsReportTemplate;
    
//    @RequestMapping(value={"/userList"}, method=RequestMethod.GET)
//	public ModelAndView userInfo(){
//		ModelAndView modelAndView = new ModelAndView();
//	    modelAndView.addObject("list", userService.findAllUsers());
//		modelAndView.setViewName("userList");
//		return modelAndView;
//	}
    
//    @RequestMapping("/userForm")  
//    public String showform(ModelMap model){  
//    	model.addAttribute("user", new User());
//        return "userForm";
//    }
    
//    @ModelAttribute("states")
//    public List<String> initializeCountries() {
//        List<String> countries = new ArrayList<String>();
//        countries.add("1");
//        countries.add("2");
//        countries.add("3");
//        countries.add("4");
////        countries.add("Active");
////        countries.add("Inactive");
////        countries.add("Deleted");
////        countries.add("Locked");
//        return countries;
//    }
    
    @RequestMapping(value = "/user/enabledStatus", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody Map<String, Object> initializeUserStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
//		if (list != null) {
			map.put("data", list);
//		} else {
//			map.put("data", new ArrayList<ModuleView>());
//		}
		return map;
	}
    
//    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
//    public String saveUser(@Valid User user,
//            BindingResult result,Principal principal,ModelMap model) {
//    	
//        if (result.hasErrors()) {
//            System.out.println("There are errors");
//            return "userForm";
//        }
//        user.setCreatedBy(principal.getName());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.save(user);
//        return "redirect:/userList";
//    }
//    
//    @RequestMapping("/editUser/{id}")  
//    public ModelAndView editUser(@PathVariable int id){ 
//    	User user = userService.findUserById(Long.valueOf(id));
//    	UserEditView userEditView = (UserEditView)BeanParser.parseObjectToNewClass(user, UserEditView.class, null);
//        return new ModelAndView("userEditForm","userEditView",userEditView);  
//    }
//    
//    @RequestMapping(value = "/saveUserEdit", method = RequestMethod.POST)
//    public String saveUserEdit(@Valid UserEditView userEditView,
//            BindingResult result,Principal principal,ModelMap model) {
//    	
//        if (result.hasErrors()) {
//            System.out.println("There are errors");
//            return "userEditForm";
//        }
//        User user = (User)BeanParser.parseObjectToNewClass(userEditView, User.class, null);
//		user.setUpdatedBy(principal.getName());
//        userService.save(user);
//        return "redirect:/userList";
//    }
//    
//    @RequestMapping("/deleteUser/{id}")
//    public ModelAndView deleteUser(@PathVariable int id){ 
//    	User user = userService.findUserById(Long.valueOf(id));
//    	userService.delete(user);
//        return new ModelAndView("redirect:/userList");  
//    }
    
    @RequestMapping("/resetPasswordForm/{id}")
    public String resetPasswordForm(ModelMap model,@PathVariable Integer id){ 
    	ResetUserView resetUserView = new ResetUserView();
    	if (id !=null) {
    		User user = userService.findUserById(Long.valueOf(id));
    		resetUserView = (ResetUserView)BeanParser.parseObjectToNewClass(user, ResetUserView.class, null);
		}
    	model.addAttribute("resetUserView", resetUserView);
        return "resetPasswordForm";
    }
    @RequestMapping("/resetPasswordForm")
    public String resetPasswordForm(ModelMap model){
    	model.addAttribute("resetUserView", new ResetUserView());
        return "resetPasswordForm";
    }
    
    
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(@Valid ResetUserView resetUserView,BindingResult result,Principal principal) {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("redirect:/userList");
    	
        if (result.hasErrors()) {
            return modelAndView;
        }
        
        
        try {
        	
        	if (!resetUserView.getPassword().equals(resetUserView.getNewPasswordAgain())) {
    			throw new BusinessException("The new password aren't same! Try again please.");
    		}
        	
        	User user = (User)BeanParser.parseObjectToNewClass(resetUserView, User.class, null);
            user.setUpdatedBy(user.getId()==null?principal.getName():user.getUpdatedBy());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
        	userService.savePassword(user);
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
			modelAndView.setViewName("resetPasswordForm");
			return modelAndView;
		}
        modelAndView.addObject("message", "Password was updated successfully.");
        
		return modelAndView;
    }
    
    
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
		
		if(CommonUtil.containElemnts(users)){
			beans.put("listUsers", users);
			beans.put("dividedInto", users.size());
		}else{
			beans.put("listUsers", new ArrayList<User>());
			beans.put("dividedInto", "");
		}
    	
    	ExcelUtils.createReport(this.getClass(), beans, reportTemplate, templateFileName, response);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(value={"/user"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		return modelAndView;
	}
    
    @RequestMapping(value = "/user/list", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody Map<String, Object> getAll() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserEditView> list = castUserToUserEditViewList(userService.findAllUsers());
        if (list != null) {
            map.put("data", list);
        } else {
      	  map.put("data", new ArrayList<ModuleView>());
        }
        return map;
    }
    
    public List<UserEditView> castUserToUserEditViewList(List<User> users){
		List<UserEditView> userEditViews = new ArrayList<UserEditView>();
		for (User user : users) {
			userEditViews.add((UserEditView)BeanParser.parseObjectToNewClass(user, UserEditView.class, null));
		}
		return userEditViews;
	}
    
    @RequestMapping(value = "/user/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid UserEditView userEditView,BindingResult result) {
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
        
		userService.save((User) BeanParser.parseObjectToNewClass(userEditView, User.class, null));
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
    
    @RequestMapping(value={"/user/delete"}, method={RequestMethod.POST})
	public @ResponseBody Map<String, Object> delete(@RequestBody UserEditView userEditView){
		Map<String, Object> map = new HashMap<String, Object>();
		User user = userService.findUserById(Long.valueOf(userEditView.getId()));
		userService.delete(user);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
	}
    
    @RequestMapping(value = "/user/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody UserEditView userEditView) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findUserById(userEditView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (UserEditView)BeanParser.parseObjectToNewClass(user, UserEditView.class, null));
        return map;
    }
    
}
