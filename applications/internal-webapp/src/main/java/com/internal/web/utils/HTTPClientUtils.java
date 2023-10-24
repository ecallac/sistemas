/**
 * 
 */
package com.internal.web.utils;

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
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;

/**
 * @author efrain
 *
 */
public class HTTPClientUtils {
	private static Logger logger = Logger.getLogger(HTTPClientUtils.class);
	public static String sendGetRequest(String url,String contentType) throws Exception {
		HttpUriRequest request = new HttpGet(url.replace(" ","%20"));
		request.addHeader("accept", contentType);
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).build();
		logger.info("Connecting GET to : "+url + " with content type : "+contentType);
		HttpResponse httpResponse = HttpClientBuilder.create()
													 .setDefaultRequestConfig(requestConfig)
													 .build()
													 .execute(request);
		return manageResponse(httpResponse);
	}
	
	public static String sendPostRequest(String url, String contentType, String requestData) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).build();
		logger.info("Connecting POST to : "+url + " with content type : "+contentType);
		CloseableHttpClient httpClient = HttpClientBuilder.create()
														.setDefaultRequestConfig(requestConfig)
														.build();
		HttpPost httpPost = new HttpPost(url);
		logger.info("Request : "+requestData);
		StringEntity input = new StringEntity(requestData);
		input.setContentType(contentType);
		httpPost.setEntity(input);

		HttpResponse httpResponse = httpClient.execute(httpPost);
		return manageResponse(httpResponse);
	}

	private static String manageResponse(HttpResponse httpResponse) throws Exception{
		HttpEntity entity = httpResponse.getEntity();
		String response= null;
		if (entity!=null){
			response = EntityUtils.toString(entity, "UTF-8");
		}
		logger.info("Response : "+response);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK || httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT) {
			return response;
		} else {
			throw new HttpResponseException(httpResponse.getStatusLine().getStatusCode(), response);
		}
	}
}
