/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Sucursal;
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
public class SucursalIntegration extends ServiceIntegrationAbstract<Sucursal> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="sucursal";

	public List<Sucursal> findList()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Sucursal>>(){});
	}
	public List<Sucursal> findActivos(Long organizacionId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos?id="+organizacionId,new TypeReference<List<Sucursal>>(){});
	}
	public Sucursal findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Sucursal.class);
	}
	public Sucursal save(Sucursal entity)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Sucursal.class);
	}
	public List<Sucursal> save(List<Sucursal> entity) throws Exception  {
		return (List<Sucursal>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Sucursal>>(){});
	}
	public Sucursal findByNombre(String nombre) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByNombre?nombre="+ nombre, Sucursal.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Sucursal>>(){});
	}
}
