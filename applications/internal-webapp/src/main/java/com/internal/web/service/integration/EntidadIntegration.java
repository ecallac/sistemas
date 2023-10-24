/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Organizacion;
import com.common.Persona;
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
public class EntidadIntegration extends ServiceIntegrationAbstract<Persona> {

	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="entidad";


}
