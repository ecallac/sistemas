/**
 * 
 */
package com.internal.web.service.integration;

import java.util.List;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Persona;
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
	
	String basePath="persona";
	
	public Persona findByEntidadRolId(Long entidadRolId) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findPersonaByEntidadRolId?entidadRolId="+entidadRolId, Persona.class);
	}
	public List<Persona> findByTermino(String termino) throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findPersonaByTermino?termino="+termino,new TypeReference<List<Persona>>(){});
	}
	public Persona save(Persona persona)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", persona,Persona.class);
	}
	public Persona findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Persona.class);
	}
	public List<Persona> save(List<Persona> entity) throws Exception  {
		return (List<Persona>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Persona>>(){});
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Persona>>(){});
	}
	public Persona saveEntidad(Persona persona)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/savePersona", persona,Persona.class);
	}
}
