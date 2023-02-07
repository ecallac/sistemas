/**
 * 
 */
package com.internal.web.service.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Componente;
import com.common.TipoBase;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.common.TipoBase;


/**
 * @author efrain.calla
 *
 */
@Service
public class TipoBaseIntegration extends ServiceIntegrationAbstract<TipoBase> {
	@Autowired
	@Value("${app.common.api}")
	private String api;
	
	String basePath="tipobase";
	
	public TipoBase findById(Long id) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findById?id="+id, TipoBase.class);
	}
	public TipoBase findByCodigo(String codigo) throws Exception  {
		return getObjectFromGetRequest(api+"/"+basePath+"/findByCodigo?codigo="+codigo, TipoBase.class);
	}
	public List<TipoBase> findAll() throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findAll",new TypeReference<List<TipoBase>>(){});
	}
	public Map<String, TipoBase> findAllMap()  throws Exception {
		List<TipoBase> tipoBases = findAll();
		return !CollectionUtils.isEmpty(tipoBases)? tipoBases.stream().collect(Collectors.toMap(TipoBase::getCodigo,Function.identity())):new HashMap<String, TipoBase>();
	}
	public List<TipoBase> findByCategoriaActivos(String categoria) throws Exception  {
		return getObjectListFromGetRequest(api+"/"+basePath+"/findByCategoriaActivos?categoria="+categoria,new TypeReference<List<TipoBase>>(){});
	}
	public Map<String, TipoBase> findByCategoriaActivosMap(String categoria)  throws Exception {
		List<TipoBase> tipoBases = findByCategoriaActivos(categoria);
		return !CollectionUtils.isEmpty(tipoBases)? tipoBases.stream().collect(Collectors.toMap(TipoBase::getCodigo,Function.identity())):new HashMap<String, TipoBase>();
	}
	public TipoBase save(TipoBase entity) throws Exception  {
		return setObjectToPostRequest(api+"/"+basePath+"/save", entity,TipoBase.class);
	}
	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception  {
		return (DataTablesOutput) doPostRequestGeneral(api+"/"+basePath+"/findDataTables", entity,new TypeReference<DataTablesOutput<TipoBase>>(){});
	}
}
