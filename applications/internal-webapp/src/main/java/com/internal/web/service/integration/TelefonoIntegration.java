/**
 * 
 */
package com.internal.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.Telefono;


/**
 * @author efrain.calla
 *
 */
@Service
public class TelefonoIntegration extends ServiceIntegrationAbstract<Telefono> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="telefono";
	
	public Telefono findById(Long id) {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Telefono.class);
	}
	public List<Telefono> findByEntidadId(Long entidadId) {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByEntidadId?entidadId="+entidadId,new TypeReference<List<Telefono>>(){});
	}
	public Telefono save(Telefono telefono) {
		return setObjectToPostRequest(api+"/"+basePath+"/save", telefono,Telefono.class);
	}
}
