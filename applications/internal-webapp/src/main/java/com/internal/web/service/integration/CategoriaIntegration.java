/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Categoria;
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
public class CategoriaIntegration extends ServiceIntegrationAbstract<Categoria> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="categoria";

	public List<Categoria> findList()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Categoria>>(){});
	}
	public List<Categoria> findActivos()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Categoria>>(){});
	}
	public Categoria findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Categoria.class);
	}
	public List<Categoria> findByParentCategoriaId(Long parentCategoriaId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByParentCategoriaId?parentCategoriaId="+parentCategoriaId,new TypeReference<List<Categoria>>(){});
	}
	public Categoria save(Categoria entity)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Categoria.class);
	}
	public List<Categoria> save(List<Categoria> entity) throws Exception  {
		return (List<Categoria>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Categoria>>(){});
	}
	public Categoria findByNombre(String nombre) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByNombre?nombre="+ nombre, Categoria.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Categoria>>(){});
	}
}
