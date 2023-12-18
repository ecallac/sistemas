/**
 * 
 */
package com.internal.web.service.integration;

import com.common.Ubigeo;
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
public class UbigeoIntegration extends ServiceIntegrationAbstract<Ubigeo> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="ubigeo";
	
	public Ubigeo findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Ubigeo.class);
	}
	public List<Ubigeo> findByParentUbigeoId(Long parentUbigeoId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByParentUbigeoId?parentUbigeoId="+(parentUbigeoId==null?"":parentUbigeoId),new TypeReference<List<Ubigeo>>(){});
	}
	public Ubigeo save(Ubigeo ubigeo)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", ubigeo,Ubigeo.class);
	}
}
