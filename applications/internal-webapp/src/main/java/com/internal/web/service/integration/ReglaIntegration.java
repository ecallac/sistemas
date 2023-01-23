/**
 * 
 */
package com.internal.web.service.integration;

import com.common.ReglaDetalle;
import com.common.Telefono;
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
public class ReglaIntegration extends ServiceIntegrationAbstract<ReglaDetalle> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="regla";

	public List<ReglaDetalle> findByCategoria(String categoria) {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByCategoria?categoria="+categoria,new TypeReference<List<ReglaDetalle>>(){});
	}

}
