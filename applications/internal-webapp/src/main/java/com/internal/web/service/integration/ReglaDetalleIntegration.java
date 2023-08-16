/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.ReglaDetalle;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author efrain.calla
 *
 */
@Service
public class ReglaDetalleIntegration extends ServiceIntegrationAbstract<ReglaDetalle> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="reglaDetalle";

	public List<ReglaDetalle> findByCategoria(String categoria) throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByCategoria?categoria="+categoria,new TypeReference<List<ReglaDetalle>>(){});
	}
	public List<ReglaDetalle> findByCodigo(String codigo)  throws Exception {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByCodigo?codigo="+codigo,new TypeReference<List<ReglaDetalle>>(){});
	}

	public Map<String,ReglaDetalle> getReglasMap(List<ReglaDetalle> reglas){
		Map<String, ReglaDetalle> map = reglas.stream().collect(Collectors.toMap(ReglaDetalle::getCondicion, Function.identity()));
		return map;
	}
	public ReglaDetalle findById(Long id)  throws Exception {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, ReglaDetalle.class);
	}
	public ReglaDetalle save(ReglaDetalle entity)  throws Exception {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,ReglaDetalle.class);
	}
	public List<ReglaDetalle> save(List<ReglaDetalle> entity) throws Exception  {
		return (List<ReglaDetalle>) doPostRequestGeneral(api+"/"+basePath+"/saveList", entity,new TypeReference<List<ReglaDetalle>>(){});
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<ReglaDetalle>>(){});
	}
}
