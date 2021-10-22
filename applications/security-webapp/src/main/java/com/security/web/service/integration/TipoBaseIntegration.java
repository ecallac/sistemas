/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.TipoBase;


/**
 * @author efrain.calla
 *
 */
@Service
public class TipoBaseIntegration extends ServiceIntegrationAbstract<TipoBase> {
	@Autowired
	@Value("${app.common.api}")
	private String appsecurityapi;
	
	String basePath="tipobase";
	
	public TipoBase findById(Long id) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findById?id="+id, TipoBase.class);
	}
	public TipoBase findByCodigo(String codigo) {
		return getObjectFromGetRequest(appsecurityapi+"/"+basePath+"/findByCodigo?codigo="+codigo, TipoBase.class);
	}
	public List<TipoBase> findByCategoriaActivos(String categoria) {
		return getObjectListFromGetRequest(appsecurityapi+"/"+basePath+"/findByCategoriaActivos?categoria="+categoria,new TypeReference<List<TipoBase>>(){});
	}
}
