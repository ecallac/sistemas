/**
 * 
 */
package com.internal.web.service.integration;

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
	private String api;
	
	String basePath="entidad";
	
	public Persona findByEntidadRolId(Long entidadRolId) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findPersonaByEntidadRolId?entidadRolId="+entidadRolId, Persona.class);
	}
	public List<Persona> findByTermino(String termino) throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findPersonaByTermino?termino="+termino,new TypeReference<List<Persona>>(){});
	}
	public Persona save(Persona persona)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/savePersona", persona,Persona.class);
	}
}
