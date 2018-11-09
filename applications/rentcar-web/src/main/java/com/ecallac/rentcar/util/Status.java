/**
 * 
 */
package com.ecallac.rentcar.util;

/**
 * @author efrain.calla
 *
 */
public enum Status {
	STATUS_TXT("status"),
	MESSAGE_TXT("message"),
	OK("OK"),
	ERROR("ERROR"),
	
	NEW("N"),
	ENABLED("Y"),
	DELETED("D");
	private String code;
	
	private Status(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
