/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Cargo;
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
public class CargoIntegration extends ServiceIntegrationAbstract<Cargo> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="cargo";

	public List<Cargo> findList()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Cargo>>(){});
	}
	public List<Cargo> findActivos()  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Cargo>>(){});
	}
	public Cargo findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Cargo.class);
	}
	public List<Cargo> findByParentCargoId(Long parentCargoId)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByParentCargoId?parentCargoId="+parentCargoId,new TypeReference<List<Cargo>>(){});
	}
	public Cargo save(Cargo entity)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Cargo.class);
	}
	public List<Cargo> save(List<Cargo> entity) throws Exception  {
		return (List<Cargo>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Cargo>>(){});
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Cargo>>(){});
	}
}
