/**
 * 
 */
package com.internal.web.service.integration;

import com.common.Direccion;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


/**r
 * @author efrain.calla
 *
 */
@Service
public class DireccionIntegration extends ServiceIntegrationAbstract<Direccion> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="direccion";
	
	public Direccion findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Direccion.class);
	}
	public List<Direccion> findByEntidadId(Long entidadId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByEntidadId?entidadId="+entidadId,new TypeReference<List<Direccion>>(){});
	}
	public Direccion save(Direccion direccion)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", direccion,Direccion.class);
	}
}
