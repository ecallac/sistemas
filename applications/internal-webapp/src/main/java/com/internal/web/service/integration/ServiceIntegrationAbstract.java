/**
 * 
 */
package com.internal.web.service.integration;

import java.util.List;

import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.internal.web.utils.HTTPClientUtils;

/**
 * @author efrain.calla
 *
 */
public abstract class ServiceIntegrationAbstract<T>{
	private Logger logger = Logger.getLogger(this.getClass());
	@SuppressWarnings("unchecked")
	protected T getObjectFromJsonWithDateFormat(String json,Class<?> target) {
		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
		return (T) gson.fromJson(json, target);
	}
	
	
	
	
	protected T getObjectFromGetRequest(String url,Class<?> targetClass) throws Exception {
		String jsonResponse = HTTPClientUtils.sendGetRequest(url, ContentType.APPLICATION_JSON.getMimeType());
		return getObjectFromJsonWithDateFormat(jsonResponse, targetClass);
	}
	
	@SuppressWarnings("rawtypes")
	protected List<T> getObjectListFromGetRequest(String url,TypeReference type) throws Exception {
		String jsonResponse = HTTPClientUtils.sendGetRequest(url, ContentType.APPLICATION_JSON.getMimeType());
		if (jsonResponse!=null){
			return new ObjectMapper().readValue(jsonResponse, type);
		}
		return null;
	}
	
	protected void setObjectToPostRequest(String url,Object bean) throws Exception {
		  String jsonRequest = new ObjectMapper().writeValueAsString(bean);
		  String jsonResponse = HTTPClientUtils.sendPostRequest(url, ContentType.APPLICATION_JSON.getMimeType(),jsonRequest);
	}
	
	protected T setObjectToPostRequest(String url,Object bean,Class<?> targetClass) throws Exception {
		  String jsonRequest = new ObjectMapper().writeValueAsString(bean);
		  String jsonResponse = HTTPClientUtils.sendPostRequest(url, ContentType.APPLICATION_JSON.getMimeType(),jsonRequest);
		return getObjectFromJsonWithDateFormat(jsonResponse, targetClass);
	}
	protected Object doPostRequestGeneral(String url,Object bean,TypeReference type) throws Exception {
		String jsonRequest = new ObjectMapper().writeValueAsString(bean);
		String jsonResponse = HTTPClientUtils.sendPostRequest(url, ContentType.APPLICATION_JSON.getMimeType(),jsonRequest);
		return getGeneralObjectFromJsonWithDateFormat(jsonResponse, type);
	}
	protected Object getGeneralObjectFromJsonWithDateFormat(String json,TypeReference type) {
		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
		return gson.fromJson(json, type.getType());
	}
}
