/**
 * 
 */
package com.security.domain;

/**
 * @author EFRAIN
 * @dateCreated 1 may. 2017 11:53:44
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3541481886833699523L;
	
	public BusinessException (String message){
		super(message);
	}
}
