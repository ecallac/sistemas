/**
 * 
 */
package com.security.client.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author efrain.calla
 *
 */
public class RestUtils {
	private static final Log log = LogFactory.getLog(RestUtils.class);
	  
	  public static <Req, Res> Res sendXMLPostRequest(String url, Req request, Class<Req> clazz, Class<Res> clazzResponse)
	  {
	    Res response = null;
	    log.info("Sending POST Message to service: '" + url + "'");
	    
	    String xml = XmlUtils.toXml(clazz, request);
	    log.info("XML requet body --> \n" + xml);
	    try
	    {
	      HttpResponse<String> httpResponse = Unirest.post(url)
	    		  .header("Content-Type", "application/xml")
	    		  .header("accept", "application/xml")
	    		  .body(xml)
	    		  .asString();
	      if ((httpResponse.getStatus() == 200) || (httpResponse.getStatus() == 201)) {
	    	  log.info("XML response body --> \n" + (String)httpResponse.getBody());
	        response = XmlUtils.fromXml(clazzResponse, (String)httpResponse.getBody());
	      }else{
	    	  log.info("There was an error: "+ httpResponse.getStatus()+ " message: "+httpResponse.getStatusText());
	      }
	    }
	    catch (UnirestException e)
	    {
	      log.error("Error when deserializing response from " + url, e);
	    }
	    return response;
	  }
}
