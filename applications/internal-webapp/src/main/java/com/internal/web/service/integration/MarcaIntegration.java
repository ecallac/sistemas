/**
 * 
 */
package com.internal.web.service.integration;

import com.common.Marca;
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
public class MarcaIntegration extends ServiceIntegrationAbstract<Marca> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="marca";

	public List<Marca> findList() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Marca>>(){});
	}
	public List<Marca> findActivos() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Marca>>(){});
	}
	public Marca findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Marca.class);
	}
	public Marca save(Marca entity)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Marca.class);
	}
}
