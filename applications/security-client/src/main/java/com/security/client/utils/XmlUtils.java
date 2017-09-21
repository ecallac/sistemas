/**
 * 
 */
package com.security.client.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author efrain.calla
 *
 */
public class XmlUtils {
	private static final Log log = LogFactory.getLog(XmlUtils.class);
	  
	  public static <T> String toXml(Class<T> clazz, T serializeMe)
	  {
	    log.info("Serializing object from class " + clazz.getSimpleName());
	    XStream xstream = new XStream(new DomDriver());
	    xstream.alias(clazz.getSimpleName(), clazz);
	    String result = xstream.toXML(serializeMe);
	    log.info("Serialization finished.");
	    return result;
	  }
	  
	  public static <T> T fromXml(Class<T> clazz, String xml)
	  {
	    log.info("Deserializing object from class " + clazz.getSimpleName());
	    XStream xstream = new XStream();
	    xstream.alias(clazz.getSimpleName(), clazz);
	    T result = clazz.cast(xstream.fromXML(xml));
	    log.info("Deserialization finished.");
	    return result;
	  }
}
