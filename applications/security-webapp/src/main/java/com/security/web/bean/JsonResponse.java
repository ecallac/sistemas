package com.security.web.bean;

public class JsonResponse {
	private String status = null;
	private Object result = null;
	private Object resultSecond = null;
	private String urlServer;
	private String urlServerSecond;
	
	private String errorMessageResult = null;
	private String contextPath;
	
	public String getErrorMessageResult() {
		return errorMessageResult;
	}
	public void setErrorMessageResult(String errorMessageResult) {
		this.errorMessageResult = errorMessageResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getUrlServer() {
		return urlServer;
	}
	public void setUrlServer(String urlServer) {
		this.urlServer = urlServer;
	}
	public Object getResultSecond() {
		return resultSecond;
	}
	public void setResultSecond(Object resultSecond) {
		this.resultSecond = resultSecond;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getUrlServerSecond() {
		return urlServerSecond;
	}
	public void setUrlServerSecond(String urlServerSecond) {
		this.urlServerSecond = urlServerSecond;
	}
	
	
	
}
