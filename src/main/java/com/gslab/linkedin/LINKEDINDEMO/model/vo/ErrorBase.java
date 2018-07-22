package com.gslab.linkedin.linkedindemo.model.vo;

public class ErrorBase {

	private int statusCode;
	private String errorMessage;
	private String errorType;
	
	
	public ErrorBase(int statusCode, String errorMessage, String errorType) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorType = errorType;
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
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	
}
