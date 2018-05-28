package com.security.web.service.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.client.bean.EntidadRoleBean;
import com.common.client.bean.TipoBaseBean;
import com.common.client.canonical.CanonicalResponse;
import com.common.utils.CommonConstants;
import com.security.web.bean.UserView;
import com.security.web.utils.HTTPClientUtils;

@Service("commonServiceIntegration")
public class CommonServiceIntegrationImpl implements CommonServiceIntegration {

	@Autowired
	@Value("${security.common.tipoBaseByCategory}")
	private String tipoBaseByCategory;
	
	@Autowired
	@Value("${security.common.tipoBaseByCodigo}")
	private String tipoBaseByCodigo;
	
	@Autowired
	@Value("${security.common.entidad.personaPorTermino}")
	private String personaPorTermino;
	
	@Autowired
	@Value("${security.common.entidadRol.save}")
	private String saveEntidadRol;
	
	
	
	public String getPersonaPorTermino() {
		return personaPorTermino;
	}

	public String getTipoBaseByCategory() {
		return tipoBaseByCategory;
	}

	@Override
	public List<TipoBaseBean> getTipoBasesXCategoriasActivas(String categoria) {
		// TODO Auto-generated method stub
		String responseString = HTTPClientUtils.sendGetRequest(tipoBaseByCategory+"?categoria="+categoria, CommonConstants.JSON);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			CanonicalResponse canonicalResponse = mapper.readValue(responseString, CanonicalResponse.class);
			if (canonicalResponse.getStatus().equals(CanonicalResponse.STATUS_OK)) {
				Object object = canonicalResponse.getObjectBean();
				List<Map<Object, Object>> maps = (List<Map<Object, Object>>) object;
				List<TipoBaseBean> list = new ArrayList<TipoBaseBean>();
					for (Map<Object, Object> map2 : maps) {
						Integer id = (Integer) map2.get("id");
						String codigo = (String) map2.get("codigo");
						String descripcion = (String) map2.get("descripcion");
						String activo = (String) map2.get("activo");
						TipoBaseBean tipoBaseBean = new TipoBaseBean();
						tipoBaseBean.setId(Long.parseLong(id.toString()));
						tipoBaseBean.setCategoria(categoria);
						tipoBaseBean.setCodigo(codigo);
						tipoBaseBean.setDescripcion(descripcion);
						tipoBaseBean.setActivo(activo);
						list.add(tipoBaseBean);
					}
				return list;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public TipoBaseBean getTipoBasesXCodigo(String codigo) {
		String responseString = HTTPClientUtils.sendGetRequest(tipoBaseByCodigo+"?codigo="+codigo, CommonConstants.JSON);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			CanonicalResponse canonicalResponse = mapper.readValue(responseString, CanonicalResponse.class);
			if (canonicalResponse.getStatus().equals(CanonicalResponse.STATUS_OK)) {
				Object object = canonicalResponse.getObjectBean();
				Map<Object, Object> map = (Map<Object, Object>) object;
						Integer id = (Integer) map.get("id");
						String categoria = (String) map.get("categoria");
						String descripcion = (String) map.get("descripcion");
						String activo = (String) map.get("activo");
						TipoBaseBean tipoBaseBean = new TipoBaseBean();
						tipoBaseBean.setId(Long.parseLong(id.toString()));
						tipoBaseBean.setCategoria(categoria);
						tipoBaseBean.setCodigo(codigo);
						tipoBaseBean.setDescripcion(descripcion);
						tipoBaseBean.setActivo(activo);
				return tipoBaseBean;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public EntidadRoleBean saveEntidadRol(EntidadRoleBean entidadRoleBean) {
		// TODO Auto-generated method stub
		--
		return null;
	}
	
}
