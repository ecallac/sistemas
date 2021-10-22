/**
 * 
 */
package com.security.web.config;

import org.apache.http.entity.ContentType;

import com.common.Persona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.security.web.utils.HTTPClientUtils;

/**
 * @author efrain.calla
 *
 */
public class HttpClientUtilTest {
	public static void main (String [] args) {
	
	String jsonResponse = HTTPClientUtils.sendGetRequest("http://localhost:9000/entidad/findPersonaByEntidadRolId?entidadRolId=1", ContentType.APPLICATION_JSON.getMimeType());
	try {
		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
		Persona persona = gson.fromJson(jsonResponse, Persona.class);
		
		System.out.println();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
