/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Regla;
import com.common.TipoBase;
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
public class ReglaIntegration extends ServiceIntegrationAbstract<Regla> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="regla";

	public Regla findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Regla.class);
	}
	public Regla save(Regla entity) throws Exception  {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Regla.class);
	}
	public List<Regla> save(List<Regla> entity) throws Exception  {
		return (List<Regla>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Regla>>(){});
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Regla>>(){});
	}
	public Regla findByCodigo(String codigo) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByCodigo?id="+codigo, Regla.class);
	}
}
