/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.Persona;


/**
 * @author efrain.calla
 *
 */
@Service
public class PersonaIntegration extends ServiceIntegrationAbstract<Persona> {

	@Autowired
	@Value("${app.common.api}")
	private String appsecurityapi;
	
	String basePath="persona";
	
	public Persona findByEntidadRolId(Long entidadRolId) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findPersonaByEntidadRolId?entidadRolId="+entidadRolId, Persona.class);
	}
	public List<Persona> findByTermino(String termino) {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findPersonaByTermino?termino="+termino,new TypeReference<List<Persona>>(){});
	}
	public Persona save(Persona persona) {
		return setObjectToPostRequest(appsecurityapi+"/"+basePath+"/savePersona", persona,Persona.class);
	}
}
