/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Area;
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
public class AreaIntegration extends ServiceIntegrationAbstract<Area> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="area";

	public List<Area> findList() throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findList",new TypeReference<List<Area>>(){});
	}
	public List<Area> findActivos() throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findActivos",new TypeReference<List<Area>>(){});
	}
	public Area findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Area.class);
	}
	public List<Area> findByParentAreaId(Long parentAreaId) throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByParentAreaId?parentAreaId="+parentAreaId,new TypeReference<List<Area>>(){});
	}
	public Area save(Area entity) throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,Area.class);
	}
	public List<Area> save(List<Area> entity) throws Exception  {
		return (List<Area>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Area>>(){});
	}
	public Area findByNombre(String nombre) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByNombre?nombre="+nombre, Area.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Area>>(){});
	}
}
