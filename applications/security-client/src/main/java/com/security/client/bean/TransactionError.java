/**
 * 
 */
package com.security.client.bean;

/**
 * @author efrain.calla
 *
 */
import java.io.Serializable;

public class TransactionError
  implements Serializable
{
  private static final long serialVersionUID = -1451619325897998577L;
  private String errorCode;
  private String errorDescription;
  
  public TransactionError() {}
  
  public TransactionError(String errorCode)
  {
    this.errorCode = errorCode;
    this.errorDescription = errorCode;
  }
  
  public TransactionError(String errorCode, String errorDescription)
  {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }
  
  public String getErrorCode()
  {
    return this.errorCode;
  }
  
  public void setErrorCode(String errorCode)
  {
    this.errorCode = errorCode;
  }
  
  public String getErrorDescription()
  {
    return this.errorDescription;
  }
  
  public void setErrorDescription(String errorDescription)
  {
    this.errorDescription = errorDescription;
  }
  
  public String toString()
  {
    return "\nerrorCode: " + this.errorCode + "\nerrorDescription: " + this.errorDescription;
  }
}