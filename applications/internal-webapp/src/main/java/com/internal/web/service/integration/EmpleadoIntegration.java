/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Empleado;
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
public class EmpleadoIntegration extends ServiceIntegrationAbstract<Empleado> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="empleado";

	public List<Empleado> findList()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Empleado>>(){});
	}
	public List<Empleado> findActivos()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Empleado>>(){});
	}
	public Empleado findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Empleado.class);
	}
	public List<Empleado> findByParentEmpleadoId(Long parentEmpleadoId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByParentEmpleadoId?parentEmpleadoId="+parentEmpleadoId,new TypeReference<List<Empleado>>(){});
	}
	public Empleado save(Empleado entity)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Empleado.class);
	}
	public List<Empleado> save(List<Empleado> entity) throws Exception  {
		return (List<Empleado>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Empleado>>(){});
	}
	public Empleado findByCodigo(String codigo) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByCodigo?codigo="+ codigo, Empleado.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Empleado>>(){});
	}
}
