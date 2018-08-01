package com.gslab.linkedin.linkedindemo.model.vo;


public class ErrorBase {

	private int statusCode;
	private String errorMessage;
	private String errorKey;
	
	
	public ErrorBase(int statusCode, String errorMessage, String errorType) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorKey = errorType;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorType() {
		return errorKey;
	}
	public void setErrorType(String errorType) {
		this.errorKey = errorType;
	}
	
	
}
