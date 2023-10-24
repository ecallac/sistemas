/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Organizacion;
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
public class OrganizacionIntegration extends ServiceIntegrationAbstract<Organizacion> {

	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="organizacion";
	
	public Organizacion findByEntidadRolId(Long entidadRolId) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findOrganizacionByEntidadRolId?entidadRolId="+entidadRolId, Organizacion.class);
	}
	public List<Organizacion> findByTermino(String termino) throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findOrganizacionByTermino?termino="+termino,new TypeReference<List<Organizacion>>(){});
	}
	public Organizacion save(Organizacion organizacion)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", organizacion,Organizacion.class);
	}
	public Organizacion findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, Organizacion.class);
	}
	public List<Organizacion> save(List<Organizacion> entity) throws Exception  {
		return (List<Organizacion>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<Organizacion>>(){});
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<Organizacion>>(){});
	}
	public Organizacion saveEntidad(Organizacion organizacion)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/saveOrganizacion", organizacion,Organizacion.class);
	}
}
