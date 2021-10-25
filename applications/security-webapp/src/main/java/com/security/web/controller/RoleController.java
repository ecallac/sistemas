/**
 * 
 */
package com.security.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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

import com.BeanParser;
import com.security.Permission;
import com.security.Role;
import com.security.web.bean.PermissionRoleView;
import com.security.web.bean.RoleView;
import com.security.web.bean.State;
import com.security.web.bean.TreeNode;
import com.security.web.service.LoginService;
import com.security.web.service.integration.PermissionIntegration;
import com.security.web.service.integration.RoleIntegration;
import com.security.web.utils.SecurityConstants;
import com.security.web.utils.SecurityUtil;

/**
 * @author efrain
 *
 */
@Controller
public class RoleController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	LoginService loginService;
	@Autowired
	RoleIntegration roleIntegration;
	
	@Autowired
	PermissionIntegration permissionIntegration;
	
	@RequestMapping(value={"/role"}, method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView list(HttpSession session,Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		loginService.addSessionObjects(session,principal);
		modelAndView.setViewName("role");
		return modelAndView;
	}
	
	@RequestMapping(value = "/role/enabledRoles", method = {RequestMethod.GET,RequestMethod.POST})
	  public @ResponseBody Map<String, Object> initializeEnabledRoles() {
	      Map<String, Object> map = new HashMap<String, Object>();
	      List<RoleView> list = castRoleToRoleViewList(roleIntegration.findEnabledList());
	      if (list != null) {
	          map.put("data", list);
	      } else {
	    	  map.put("data", new ArrayList<RoleView>());
	      }
	      return map;
	  }
	
  @RequestMapping(value = "/role/list", method = {RequestMethod.GET,RequestMethod.POST})
  public @ResponseBody Map<String, Object> getAll() {
      Map<String, Object> map = new HashMap<String, Object>();
      List<RoleView> list = castRoleToRoleViewList(roleIntegration.findList());
      if (list != null) {
//          map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//          map.put(SecurityConstants.MESSAGE, "Data found");
          map.put("data", list);
      } else {
    	  map.put("data", new ArrayList<RoleView>());
//          map.put(SecurityConstants.STATUS, SecurityConstants.ERROR);
//          map.put(SecurityConstants.MESSAGE, "Data not found");
      }
      return map;
  }
	
	@RequestMapping(value = "/role/save", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> save(@RequestBody @Valid RoleView RoleView,BindingResult result,Principal principal) {
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
        
		Role role = (Role) BeanParser.parseObjectToNewClass(RoleView, Role.class, null);
		if (role.getId()==null) {
			role.setCreatedBy(principal.getName());
		} else {
			role.setUpdatedBy(principal.getName());
		}
		roleIntegration.save(role);
		
        
        map.put("validated", true);
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been saved successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
//	@RequestMapping(value={"/role/delete"}, method={RequestMethod.POST})
//	public @ResponseBody Map<String, Object> delete(@RequestBody RoleView RoleView){
//		Map<String, Object> map = new HashMap<String, Object>();
//		Role Role = securityServiceIntegration.findRoleById(Long.valueOf(RoleView.getId()));
//		securityServiceIntegration.delete(Role);
//        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
//        map.put(SecurityConstants.MESSAGE, "Your record have been deleted successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
//        return map;
//	}
	
	@RequestMapping(value = "/role/load", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> load(@RequestBody RoleView RoleView) {
        Map<String, Object> map = new HashMap<String, Object>();
        Role Role = roleIntegration.findById(RoleView.getId());
        map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put("viewBean", (RoleView)BeanParser.parseObjectToNewClass(Role, RoleView.class, null));
        return map;
    }
	
	@RequestMapping(value = "/role/enableDisable", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> enableDisable(@RequestBody RoleView RoleView,Principal principal) {
        Map<String, Object> map = new HashMap<String, Object>();
		Role role = (Role) BeanParser.parseObjectToNewClass(RoleView, Role.class, null);
		role.setUpdatedBy(principal.getName());
		roleIntegration.save(role);
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
        map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        
        return map;
    }
	
	public List<RoleView> castRoleToRoleViewList(List<Role> Roles){
		List<RoleView> RoleViews = new ArrayList<RoleView>();
		for (Role Role : Roles) {
			RoleViews.add((RoleView)BeanParser.parseObjectToNewClass(Role, RoleView.class, null));
		}
		return RoleViews;
	}
	
	@RequestMapping(value = "/role/loadPermissions", method = {RequestMethod.POST})
	public @ResponseBody  Map<String, Object> loadPermissions(@RequestBody PermissionRoleView view) {
		Map<String, Object> map = new HashMap<String, Object>();
		Role role = roleIntegration.findById(view.getRoleId());
		map.put(SecurityConstants.STATUS, SecurityConstants.OK);
		map.put("viewBean", role);
		List<Permission> permissions = new ArrayList<>();
		if (StringUtils.isEmpty(view.getModuleId())) {
			permissions = permissionIntegration.findEnabledList();
		} else {
			permissions = permissionIntegration.findEnabledListByModuleId(Long.valueOf(view.getModuleId()));
		}
		map.put("permissions", makeTreeNodeList(permissions, role.getPermissions()));
		return map;
	}
	
	private List<TreeNode> makeTreeNodeList(List<Permission> permissions,List<Permission> rolePermissions){
		Map<String, Permission> permissionMap = new HashMap<String, Permission>();
		for (Permission rolePermission : rolePermissions) {
			permissionMap.put(String.valueOf(rolePermission.getId()), rolePermission);
		}
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for (Permission permission : permissions) {
			TreeNode node = new TreeNode();
			node.setId(String.valueOf(permission.getId()));
			node.setText(permission.getDescription());
			if (permission.getParentPermission()!=null) {
				node.setParent(String.valueOf(permission.getParentPermission().getId()));
			}else {
				node.setParent("#");
			}
			State state = new State();
			if (permissionMap.containsKey(node.getId())) {
				state.setSelected(true);
			}
			node.setState(state);
			treeNodes.add(node);
		}
		return treeNodes;
	}
    
    @RequestMapping(value = "/role/saveAssignedPermissions", method = {RequestMethod.POST})
	public @ResponseBody  Map<String, Object> saveAssignedPermissions(@RequestBody PermissionRoleView view) {
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("validated", true);
        try {
        	roleIntegration.savePermissionAssociation(getRoleAndPermissions(view.getRoleId(), view.getPermissionIds()));

            map.put(SecurityConstants.STATUS, SecurityConstants.OK);
            map.put(SecurityConstants.MESSAGE, "Your record have been updated successfully at "+SecurityUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            map.put(SecurityConstants.STATUS, SecurityConstants.OK);
            map.put(SecurityConstants.MESSAGE, e.getMessage());
        }
        return map;
	}
    
    private Role getRoleAndPermissions(Long roleId, String[] permissionIds) {
    	Role role = new Role();
    	role.setId(roleId);
    	List<Permission> permissions = new ArrayList<>();
    	for (String id : permissionIds) {
    		Permission permission = new Permission();
    		permission.setId(Long.valueOf(id));
    		permissions.add(permission);
		}
    	role.setPermissions(permissions);
    	return role;
    }
}
