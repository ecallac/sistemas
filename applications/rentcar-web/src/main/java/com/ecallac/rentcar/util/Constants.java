/**
 * 
 */
package com.ecallac.rentcar.util;

/**
 * @author efrain.calla
 *
 */
public enum Constants {
	TITTLE_TXT("tittle"),
	LINKS_TXT("links"),
	DATA_TXT("data");
	private String code;
	
	private Constants(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
