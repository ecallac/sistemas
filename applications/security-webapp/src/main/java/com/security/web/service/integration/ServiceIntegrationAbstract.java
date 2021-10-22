/**
 * 
 */
package com.security.web.service.integration;

import java.util.List;

import org.apache.http.entity.ContentType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.security.web.utils.HTTPClientUtils;

/**
 * @author efrain.calla
 *
 */
public abstract class ServiceIntegrationAbstract<T>{
	
	@SuppressWarnings("unchecked")
	protected T getObjectFromJsonWithDateFormat(String json,Class<?> target) {
		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
		return (T) gson.fromJson(json, target);
	}
	
	
	
	
	protected T getObjectFromGetRequest(String url,Class<?> targetClass){
		String jsonResponse = HTTPClientUtils.sendGetRequest(url, ContentType.APPLICATION_JSON.getMimeType());
		return getObjectFromJsonWithDateFormat(jsonResponse, targetClass);
	}
	
	@SuppressWarnings("rawtypes")
	protected List<T> getObjectListFromGetRequest(String url,TypeReference type){
		String jsonResponse = HTTPClientUtils.sendGetRequest(url, ContentType.APPLICATION_JSON.getMimeType());
		try {
			return new ObjectMapper().readValue(jsonResponse, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected void setObjectToPostRequest(String url,Object bean){
		try {
			  String jsonRequest = new ObjectMapper().writeValueAsString(bean);
			  String jsonResponse = HTTPClientUtils.sendPostRequest(url, ContentType.APPLICATION_JSON.getMimeType(),jsonRequest);
			} catch (Exception e) {
			   e.printStackTrace();
			}
	}
	
	protected T setObjectToPostRequest(String url,Object bean,Class<?> targetClass){
		try {
		  String jsonRequest = new ObjectMapper().writeValueAsString(bean);
		  String jsonResponse = HTTPClientUtils.sendPostRequest(url, ContentType.APPLICATION_JSON.getMimeType(),jsonRequest);
			return getObjectFromJsonWithDateFormat(jsonResponse, targetClass);
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return null;
	}
}
