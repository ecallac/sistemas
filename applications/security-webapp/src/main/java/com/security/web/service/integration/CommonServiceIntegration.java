package com.security.web.service.integration;

import java.util.List;

import com.common.client.bean.TipoBaseBean;

public interface CommonServiceIntegration {
	List<TipoBaseBean> getTipoBasesXCategoriasActivas(String categoria);
}
