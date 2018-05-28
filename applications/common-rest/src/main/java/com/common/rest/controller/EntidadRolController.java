/**
 * 
 */
package com.common.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.client.bean.EntidadRoleBean;
import com.common.client.bean.PersonaBean;
import com.common.domain.Entidad;
import com.common.domain.EntidadRol;
import com.common.services.EntidadRoleService;
import com.common.services.EntidadService;
import com.common.utils.BeanParser;
import com.common.utils.CommonConstants;
import com.common.utils.CommonUtil;

/**
 * @author efrain
 *
 */
@RestController
public class EntidadRolController {

	@Autowired
	EntidadRoleService entidadRoleService;
	
	@RequestMapping(value = "/entidadRol/save", method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> save(@RequestBody EntidadRoleBean entidadRoleBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(":::save");
		EntidadRol entidadRol = castEntidadRolBeanToEntidadRol(entidadRoleBean);
		try {
			entidadRoleService.save(entidadRol);
			entidadRoleBean.setId(entidadRol.getId());
			map.put(CommonConstants.STATUS, CommonConstants.OK);
	        map.put(CommonConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
	        
		} catch (Exception e) {
			map.put(CommonConstants.STATUS, CommonConstants.ERROR);
	        map.put(CommonConstants.MESSAGE, e.getMessage());
	        e.printStackTrace();
		}
		
		map.put("entidadRol", entidadRoleBean);
		return map;
	}
	
	private EntidadRol castEntidadRolBeanToEntidadRol(EntidadRoleBean entidadRoleBean){
		EntidadRol entidadRol = new EntidadRol();
		entidadRol.setCreatedBy(entidadRoleBean.getCreatedBy());
		entidadRol.setEntidad(new Entidad());
		entidadRol.getEntidad().setId(entidadRoleBean.getEntidadId());
		entidadRol.setTipoEntidadrol(entidadRoleBean.getTipoEntidadRole());
		return entidadRol;
	}
}
