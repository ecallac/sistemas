/**
 * 
 */
package com.internal.web.service.integration;

import com.DataTablesInput;
import com.DataTablesOutput;
import com.common.Producto;
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
public class ProductoIntegration extends ServiceIntegrationAbstract<Producto> {
	@Autowired
	@Value("${app.common.api}")
	private String api;

	String basePath = "producto";

	public List<Producto> findList() throws Exception {
		return getObjectListFromGetRequest(api + "/" + basePath + "/findList", new TypeReference<List<Producto>>() {
		});
	}

	public Producto findById(Long id) throws Exception {
		return getObjectFromGetRequest(api + "/" + basePath + "/findById?id=" + id, Producto.class);
	}

	public Producto save(Producto entity) throws Exception {
		return setObjectToPostRequest(api + "/" + basePath + "/save", entity, Producto.class);
	}

	public List<Producto> save(List<Producto> entity) throws Exception {
		return (List<Producto>) doPostRequestGeneral(api + "/" + basePath + "/saveList", entity, new TypeReference<List<Producto>>() {
		});
	}

	public Producto findByNombre(String nombre) throws Exception {
		return getObjectFromGetRequest(api + "/" + basePath + "/findByNombre?nombre=" + nombre, Producto.class);
	}

	public DataTablesOutput findDataTables(DataTablesInput entity) throws Exception {
		return (DataTablesOutput) doPostRequestGeneral(api + "/" + basePath + "/findDataTables", entity, new TypeReference<DataTablesOutput<Producto>>() {
		});
	}
}
