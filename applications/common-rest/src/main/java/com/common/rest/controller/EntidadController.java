/**
 * 
 */
package com.common.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.client.bean.OrganizacionBean;
import com.common.client.bean.PersonaBean;
import com.common.domain.Entidad;
import com.common.domain.EntidadRol;
import com.common.domain.Organizacion;
import com.common.domain.Persona;
import com.common.domain.TipoBase;
import com.common.rest.bean.PersonaView;
import com.common.services.EntidadRoleService;
import com.common.services.EntidadService;
import com.common.services.TipoBaseService;
import com.common.utils.BeanParser;
import com.common.utils.CommonConstants;
import com.common.utils.CommonUtil;

/**
 * @author efrain
 *
 */
@RestController
public class EntidadController {
	@Autowired
	EntidadService entidadService;
	
	@Autowired
	EntidadRoleService entidadRoleService;
	
	@Autowired
	TipoBaseService tipoBaseService;
	
	@RequestMapping(value = "/entidad/personaList", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getAllPersona(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Persona> list = entidadService.findAllPersona();
		
		if (list != null) {
			map.put("data", list);
		} else {
			map.put("data", new ArrayList());
		}
		return map;
	}
	
	@RequestMapping(value = "/entidad/personaPorTermino", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public List<PersonaBean> getPersonaPorTermino(@RequestParam(value = "termino", required = true) String termino){
		List<Persona> personas =  entidadService.findPersonaPorNombreApellidoYNumeroDocumento(termino);
		List<PersonaBean> personasBeans = castPersonaListToPersonaBeanList(personas);
		return personasBeans;
	}
	
	@RequestMapping(value = "/entidad/entidadPorEntidadRolId", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getEntidadPorEntidadRolId(@RequestParam(value = "entidadRolId", required = true) String entidadRolId){
		Map<String, Object> map = new HashMap<String, Object>();
		EntidadRol entidadRol = entidadRoleService.findEntidadRolById(Long.parseLong(entidadRolId));
		TipoBase tipoBase = tipoBaseService.findTipoBaseById(Long.parseLong(entidadRol.getEntidad().getTipoEntidad()));
		if (entidadRol!=null) {
			if (CommonConstants.TIPOBASE_CODIGO_PERSONA.equals(tipoBase.getCodigo())) {
				Persona persona = entidadService.findPersonByEntityId(entidadRol.getEntidad().getId());
				PersonaBean personaBean = castPersonaToPersonaBean(persona);
				map.put("Entidad", personaBean);
				map.put("EntityType", CommonConstants.TIPOBASE_CODIGO_PERSONA);
			} else {
				Organizacion organizacion = entidadService.findOrganizacionByEntityId(entidadRol.getEntidad().getId());
				OrganizacionBean organizacionBean = castOrganizacionToOrganizacionBean(organizacion);
				map.put("Entidad", organizacionBean);
				map.put("EntityType", CommonConstants.TIPOBASE_CODIGO_ORGANIZACION);
			}
		}
		map.put(CommonConstants.STATUS, CommonConstants.OK);
		return map;
	}
	
	private List<PersonaBean> castPersonaListToPersonaBeanList(List<Persona> personas){
		List<PersonaBean> personaBeans = new ArrayList<>();
		for (Persona persona : personas) {
			PersonaBean personaBean = castPersonaToPersonaBean(persona);
			personaBeans.add(personaBean);
		}
		return personaBeans;
	}
	
	private PersonaBean castPersonaToPersonaBean(Persona persona){
		PersonaBean personaBean = (PersonaBean)BeanParser.parseObjectToNewClass(persona, PersonaBean.class, null);
		personaBean.setEntidadId(persona.getEntidad().getId());
		personaBean.setFullName(persona.getNumeroidentificacion()+ " - " +persona.getNombres()+ " " + persona.getApellidos());
		return personaBean;
	}
	
	private OrganizacionBean castOrganizacionToOrganizacionBean(Organizacion organizacion){
		OrganizacionBean organizacionBean = (OrganizacionBean)BeanParser.parseObjectToNewClass(organizacion, OrganizacionBean.class, null);
		organizacionBean.setEntidadId(organizacion.getEntidad().getId());
		return organizacionBean;
	}
	
	@RequestMapping(value = "/persona/saveNew", method = {RequestMethod.POST})
    public @ResponseBody  Map<String, Object> saveNew(@RequestBody @Valid PersonaView personaView,BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(result.hasErrors()){
	         
	         //Get error message
	         Map<String, String> errors = result.getFieldErrors().stream()
	               .collect(
	                     Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
	                 );
	         
	         map.put(CommonConstants.STATUS, CommonConstants.ERROR);
	         map.put("validated", false);
	         map.put("messages", errors);
	         return map;
	    }
		map.put("validated", true);
		
		Persona persona = (Persona) BeanParser.parseObjectToNewClass(personaView, Persona.class, null);
		try {
			persona.setFechanacimiento(CommonUtil.stringToDate(personaView.getFechanacimiento(), PersonaView.FORMAT));
		} catch (Exception e) {
			map.put(CommonConstants.STATUS, CommonConstants.ERROR);
	        map.put(CommonConstants.MESSAGE, e.getMessage());
		}
		
		Entidad entidad = new Entidad();
		entidad.setPersona(persona);
		entidad.setCreatedBy(personaView.getCreatedBy());
		entidad.setTipoEntidad(personaView.getEntidadId());
		entidad.setDateCreated(new Date());
		persona.setEntidad(entidad);
//		entidadService.save(entidad);
		entidadService.save(persona);
        
        map.put(CommonConstants.STATUS, CommonConstants.OK);
        map.put(CommonConstants.MESSAGE, "Your record have been saved successfully at "+CommonUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return map;
    }
	
}
