/**
 * 
 */
package com.common.client.canonical;

/**
 * @author EFRAIN
 * @dateCreated 18 sept. 2017 21:01:31
 */
public class CanonicalResponse {
	public static String STATUS_OK = "OK";
	public static String STATUS_ERROR = "ERROR";
	private Object objectBean;
	private String status;
	private String message;
	
	/**
	 * @return the objectBean
	 */
	public Object getObjectBean() {
		return objectBean;
	}
	/**
	 * @param objectBean the objectBean to set
	 */
	public void setObjectBean(Object objectBean) {
		this.objectBean = objectBean;
	}
	/**
	 * @return the sTATUS_OK
	 */
	public static String getSTATUS_OK() {
		return STATUS_OK;
	}
	/**
	 * @param sTATUS_OK the sTATUS_OK to set
	 */
	public static void setSTATUS_OK(String sTATUS_OK) {
		STATUS_OK = sTATUS_OK;
	}
	/**
	 * @return the sTATUS_ERROR
	 */
	public static String getSTATUS_ERROR() {
		return STATUS_ERROR;
	}
	/**
	 * @param sTATUS_ERROR the sTATUS_ERROR to set
	 */
	public static void setSTATUS_ERROR(String sTATUS_ERROR) {
		STATUS_ERROR = sTATUS_ERROR;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
