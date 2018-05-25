/**
 * 
 */
package com.common.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.client.bean.PersonaBean;
import com.common.domain.Persona;
import com.common.services.EntidadService;
import com.common.utils.BeanParser;

/**
 * @author efrain
 *
 */
@RestController
public class EntidadController {
	@Autowired
	EntidadService entidadService;
	
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
	
	private List<PersonaBean> castPersonaListToPersonaBeanList(List<Persona> personas){
		List<PersonaBean> personaBeans = new ArrayList<>();
		for (Persona persona : personas) {
			PersonaBean personaBean = (PersonaBean)BeanParser.parseObjectToNewClass(persona, PersonaBean.class, null);
			personaBean.setEntidadId(persona.getEntidad().getId());
			personaBean.setFullName(persona.getNumeroidentificacion()+ " - " +persona.getNombres()+ " " + persona.getApellidos());
			personaBeans.add(personaBean);
		}
		return personaBeans;
	}
}
