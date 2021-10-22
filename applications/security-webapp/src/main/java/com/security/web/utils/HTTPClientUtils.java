/**
 * 
 */
package com.security.web.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author efrain
 *
 */
public class HTTPClientUtils {
	public static String sendGetRequest(String url,String contentType){
		try {
		    HttpUriRequest request = new HttpGet(url);
		    request.addHeader("accept", contentType);
		    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).build();
		    System.out.println("Connecting GET to : "+url + " with content type : "+contentType);
		    HttpResponse httpResponse = HttpClientBuilder.create()
		                                                 .setDefaultRequestConfig(requestConfig)
		                                                 .build()
		                                                 .execute(request);
		    HttpEntity entity = httpResponse.getEntity();
		    String response = null;
		    if (entity!=null) {
				response = EntityUtils.toString(entity, "UTF-8");
			} 
		    System.out.println("Response : "+response);
		    if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
		        return response;
		    } else {
		        throw new HttpResponseException(httpResponse.getStatusLine().getStatusCode(), response);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String sendPostRequest(String url, String contentType, String requestData) {
		try {
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).build();
			System.out.println("Connecting POST to : "+url + " with content type : "+contentType);
			CloseableHttpClient httpClient = HttpClientBuilder.create()
															.setDefaultRequestConfig(requestConfig)
															.build();
			HttpPost httpPost = new HttpPost(url);
			System.out.println("Request : "+requestData);
			StringEntity input = new StringEntity(requestData);
			input.setContentType(contentType);
			httpPost.setEntity(input);
			CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
			
			HttpEntity entity = httpResponse.getEntity();
		    String response = EntityUtils.toString(entity, "UTF-8");
		    System.out.println("Response : "+response);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		        return response;
		    } else {
		        throw new HttpResponseException(httpResponse.getStatusLine().getStatusCode(), response);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
