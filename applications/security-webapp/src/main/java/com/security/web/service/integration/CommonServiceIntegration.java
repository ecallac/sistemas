package com.security.web.service.integration;

import java.util.List;

import com.common.client.bean.EntidadRoleBean;
import com.common.client.bean.TipoBaseBean;

public interface CommonServiceIntegration {
	String getPersonaPorTermino();
	String getTipoBaseByCategory();
	List<TipoBaseBean> getTipoBasesXCategoriasActivas(String categoria);
	TipoBaseBean getTipoBasesXCodigo(String codigo);
	EntidadRoleBean saveEntidadRol(EntidadRoleBean entidadRoleBean);
}
