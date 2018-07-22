package com.gslab.linkedin.linkedindemo.model.vo;

import java.util.List;

public class ResponseBase {

	private int statusCode;
	private String errorMessage;
	private String errorKey;
	private BeanBase payLoad;
	private List<BeanBase> payLoadList;

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

	public BeanBase getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(BeanBase payLoad) {
		this.payLoad = payLoad;
	}

	public List<BeanBase> getPayLoadList() {
		return payLoadList;
	}

	public void setPayLoadList(List<BeanBase> payLoadList) {
		this.payLoadList = payLoadList;
	}
}
