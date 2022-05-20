/**
 * 
 */
package com.internal.web.service.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.EntidadRol;


/**
 * @author efrain.calla
 *
 */
@Service
public class EntidadRolIntegration extends ServiceIntegrationAbstract<EntidadRol> {

	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="entidadRol";
	
	public EntidadRol save(EntidadRol entidadRol) {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entidadRol,EntidadRol.class);
	}
}
