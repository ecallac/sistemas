/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Componente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.internal.web.utils.HTTPClientUtils;
import org.apache.http.entity.ContentType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author efrain.calla
 *
 */
@Service
public class ComponenteIntegration extends ServiceIntegrationAbstract<Componente> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="componente";

	public List<Componente> findList() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Componente>>(){});
	}
	public List<Componente> findActivos() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Componente>>(){});
	}
	public Componente findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Componente.class);
	}
	public Componente save(Componente entity) throws Exception  {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Componente.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
//		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,DataTablesOutput.class);
//		String jsonRequest = new ObjectMapper().writeValueAsString(entity);
//		String jsonResponse = HTTPClientUtils.sendPostRequest(api+"/"+basePath+"/findDataTables", ContentType.APPLICATION_JSON.getMimeType(),jsonRequest);
//		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
//		return gson.fromJson(jsonResponse, new TypeReference<DataTablesOutput<Componente>>(){}.getType());
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Componente>>(){});
	}
}
