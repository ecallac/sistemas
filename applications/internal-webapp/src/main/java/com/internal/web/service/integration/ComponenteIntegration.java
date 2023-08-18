/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Componente;
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
public class ComponenteIntegration extends ServiceIntegrationAbstract<Componente> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="componente";

	public List<Componente> findList() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Componente>>(){});
	}
	public List<Componente> findActivos() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Componente>>(){});
	}
	public Componente findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Componente.class);
	}
	public Componente save(Componente entity) throws Exception  {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Componente.class);
	}
	public List<Componente> save(List<Componente> entity) throws Exception  {
		return (List<Componente>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Componente>>(){});
	}
	public Componente findByNombre(String nombre) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByNombre?nombre="+nombre, Componente.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Componente>>(){});
	}
}
