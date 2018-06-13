package com.security.web.service.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.common.client.bean.EntidadRoleBean;
import com.common.client.bean.PersonaBean;
import com.common.client.bean.TipoBaseBean;
import com.common.client.canonical.CanonicalResponse;
import com.security.utils.BusinessException;
import com.security.utils.SecurityConstants;
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
	
	@Autowired
	@Value("${security.common.entidad.entidadPorEntidadRolId}")
	private String entidadPorEntidadRolId;
	
	public String getPersonaPorTermino() {
		return personaPorTermino;
	}

	public String getTipoBaseByCategory() {
		return tipoBaseByCategory;
	}

	@Override
	public List<TipoBaseBean> getTipoBasesXCategoriasActivas(String categoria) {
		// TODO Auto-generated method stub
		String responseString = HTTPClientUtils.sendGetRequest(tipoBaseByCategory+"?categoria="+categoria, SecurityConstants.JSON);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			CanonicalResponse canonicalResponse = mapper.readValue(responseString, CanonicalResponse.class);
			if (canonicalResponse.getStatus().equals(CanonicalResponse.STATUS_OK)) {
				Object object = canonicalResponse.getObjectBean();
				List<Map<Object, Object>> maps = (List<Map<Object, Object>>) object;
				List<TipoBaseBean> list = new ArrayList<TipoBaseBean>();
					for (Map<Object, Object> map2 : maps) {
						Integer id = (Integer) map2.get("id");
						TipoBaseBean tipoBaseBean = new TipoBaseBean();
						tipoBaseBean.setId(Long.parseLong(id.toString()));
						tipoBaseBean.setCategoria(categoria);
						tipoBaseBean.setCodigo((String) map2.get("codigo"));
						tipoBaseBean.setDescripcion((String) map2.get("descripcion"));
						tipoBaseBean.setActivo((String) map2.get("activo"));
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
		String responseString = HTTPClientUtils.sendGetRequest(tipoBaseByCodigo+"?codigo="+codigo, SecurityConstants.JSON);
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			CanonicalResponse canonicalResponse = mapper.readValue(responseString, CanonicalResponse.class);
			if (canonicalResponse.getStatus().equals(CanonicalResponse.STATUS_OK)) {
				Object object = canonicalResponse.getObjectBean();
				Map<Object, Object> map = (Map<Object, Object>) object;
				Integer id = (Integer) map.get("id");
				TipoBaseBean tipoBaseBean = new TipoBaseBean();
				tipoBaseBean.setId(Long.parseLong(id.toString()));
				tipoBaseBean.setCategoria((String) map.get("categoria"));
				tipoBaseBean.setCodigo(codigo);
				tipoBaseBean.setDescripcion((String) map.get("descripcion"));
				tipoBaseBean.setActivo((String) map.get("activo"));
				return tipoBaseBean;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntidadRoleBean saveEntidadRol(EntidadRoleBean entidadRoleBean) throws JsonGenerationException, JsonMappingException, IOException, BusinessException {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(entidadRoleBean);
		String jsonResponse = HTTPClientUtils.sendPostRequest(saveEntidadRol, SecurityConstants.JSON, json);
		Map<String,Object> result = new ObjectMapper().readValue(jsonResponse, HashMap.class);
		if (SecurityConstants.ERROR.equals(result.get(SecurityConstants.STATUS))) {
			throw new BusinessException((String)result.get(SecurityConstants.MESSAGE));
		}else{
			Map<Object, Object> map = (Map<Object, Object>)  result.get("entidadRol");
			Integer id = (Integer) map.get("id");
			Integer entidadId = (Integer) map.get("entidadId");
			EntidadRoleBean entidadRoleBeanRes = new EntidadRoleBean();
			entidadRoleBeanRes.setId(Long.parseLong(id.toString()));
			entidadRoleBeanRes.setEntidadId(Long.parseLong(entidadId.toString()));
			entidadRoleBeanRes.setTipoEntidadRole((String) map.get("tipoEntidadRole"));
			entidadRoleBeanRes.setCreatedBy((String) map.get("createdBy"));
			return entidadRoleBeanRes;
		}
	}

	@Override
	public PersonaBean getPersonaPorEntidadRolId(Long entidadRolId) throws JsonParseException, JsonMappingException, IOException {
		String jsonResponse = HTTPClientUtils.sendGetRequest(entidadPorEntidadRolId+"?entidadRolId="+entidadRolId, SecurityConstants.JSON);
		Map<String,Object> result = new ObjectMapper().readValue(jsonResponse, HashMap.class);
		if (SecurityConstants.OK.equals(result.get(SecurityConstants.STATUS))) {
			if(SecurityConstants.TIPOBASE_CODIGO_PERSONA.equals(result.get("EntityType"))){
				Map<Object, Object> map = (Map<Object, Object>)  result.get("Entidad");
				Integer id = (Integer) map.get("id");
				Integer entidadId = (Integer) map.get("entidadId");
				Long fechanacimiento = (Long) map.get("fechanacimiento");
				PersonaBean personaBean = new PersonaBean();
				personaBean.setId(Long.parseLong(id.toString()));
				personaBean.setTipoDocumentoIdentificaion((String) map.get("tipoDocumentoIdentificaion"));
				personaBean.setNumeroidentificacion((String) map.get("numeroidentificacion"));
				personaBean.setNombres((String) map.get("nombres"));
				personaBean.setApellidos((String) map.get("apellidos"));
				personaBean.setTipoEstadoCivil((String) map.get("tipoEstadoCivil"));
				personaBean.setSexo((String) map.get("sexo"));
				personaBean.setFechanacimiento(new Date(fechanacimiento));
				personaBean.setEmail((String) map.get("email"));
				personaBean.setEntidadId(Long.parseLong(entidadId.toString()));
				personaBean.setFullName(personaBean.getNombres() + " " + personaBean.getApellidos());
				return personaBean;
			}
		}
		return null;
	}
	
}
