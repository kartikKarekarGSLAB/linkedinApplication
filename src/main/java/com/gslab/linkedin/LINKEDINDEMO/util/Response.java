package com.gslab.linkedin.linkedindemo.util;

public class Response {
	private int statusCode;
	private String errorMessage;
	private String errorKey;
	private Object payload;

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

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Response(int statusCode, String errorMessage, String errorKey, Object payload) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.errorKey = errorKey;
		this.payload = payload;
	}

	public Response() {
	}

	public Response getResponseStat() {
		return this;
	}

}
