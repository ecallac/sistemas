package com.security.web.service.integration;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.common.client.bean.EntidadRoleBean;
import com.common.client.bean.PersonaBean;
import com.common.client.bean.TipoBaseBean;
import com.security.utils.BusinessException;

public interface CommonServiceIntegration {
	String getCommonRestApp();
	String getPersonaPorTermino();
	String getTipoBaseByCategory();
	List<TipoBaseBean> getTipoBasesXCategoriasActivas(String categoria);
	TipoBaseBean getTipoBasesXCodigo(String codigo);
	EntidadRoleBean saveEntidadRol(EntidadRoleBean entidadRoleBean) throws JsonGenerationException, JsonMappingException, IOException, BusinessException;
	PersonaBean getPersonaPorEntidadRolId(Long entidadRolId) throws JsonParseException, JsonMappingException, IOException;
	
	
	
}
