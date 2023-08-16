/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Area;
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
	public List<Marca> save(List<Marca> entity) throws Exception  {
		return (List<Marca>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Marca>>(){});
	}
	public Marca findByNombre(String nombre) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByNombre?nombre="+nombre, Marca.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Marca>>(){});
	}
}
