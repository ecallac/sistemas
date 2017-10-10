package com.security.web.service.integration;

import java.io.IOException;
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

import com.common.client.bean.TipoBaseBean;
import com.common.utils.CommonConstants;
import com.security.web.utils.HTTPClientUtils;

@Service
public class CommonServiceIntegrationImpl implements CommonServiceIntegration {

	@Autowired
	@Value("${security.common.tipoBaseByCategory}")
	private String tipoBaseByCategory;
	
	@Override
	public List<TipoBaseBean> getTipoBasesXCategoriasActivas(String categoria) {
		// TODO Auto-generated method stub
		String responseString = HTTPClientUtils.sendGetRequest(tipoBaseByCategory+"?categoria="+categoria, CommonConstants.JSON);
		Map<String, Object> map = convertJsonStringToMap(responseString);
		
		return null;
	}
	
	private Map<String,Object> convertJsonStringToMap(String jsonString){
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			ObjectMapper mapper = new ObjectMapper();
			// convert JSON string to Map
			map = mapper.readValue(jsonString, new TypeReference<Map<String, String>>(){});

			System.out.println(map);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
}
