package com.gslab.linkedin.linkedindemo.model.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseBase {

	private Status status;
	private BeanBase payLoad;
	private List<? extends BeanBase> payLoadList;

	public ResponseBase() {
		// TODO Auto-generated constructor stub
		this.status = new Status();
	}

	public ResponseBase(List<? extends BeanBase> payLoadList) {
		super();
		this.status = new Status();
		this.payLoadList = payLoadList;
	}

	public ResponseBase(BeanBase payLoad) {
		super();
		this.status = new Status();
		this.payLoad = payLoad;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BeanBase getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(BeanBase payLoad) {
		this.payLoad = payLoad;
	}

	public List<? extends BeanBase> getPayLoadList() {
		return payLoadList;
	}

	public void setPayLoadList(List<BeanBase> payLoadList) {
		this.payLoadList = payLoadList;
	}
}
