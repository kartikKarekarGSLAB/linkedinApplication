package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Status {

	private int statusCode;
	
	private String errorKey;
	
	private String errorMessage;

	public Status() {
		// TODO Auto-generated constructor stub
		this.statusCode = 200;
		this.errorMessage = "SUCCESS";
	}
	public Status(int statusCode, String errorKey, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.errorKey = errorKey;
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
