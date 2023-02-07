/**
 * 
 */
package com.internal.web.service.integration;

import com.common.EntidadRolAtributo;
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
public class EntidadRolAtributoIntegration extends ServiceIntegrationAbstract<EntidadRolAtributo> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="entidadRolAtributo";
	
	public EntidadRolAtributo findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, EntidadRolAtributo.class);
	}
	public List<EntidadRolAtributo> findByEntidadRolId(Long entidadRolId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByEntidadRolId?entidadRolId="+entidadRolId,new TypeReference<List<EntidadRolAtributo>>(){});
	}
	public EntidadRolAtributo save(EntidadRolAtributo objeto)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", objeto,EntidadRolAtributo.class);
	}
}
