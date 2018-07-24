package com.gslab.linkedin.linkedindemo.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseBase {

	private Status status; 
	private BeanBase payLoad;
	private List<BeanBase> payLoadList;
 
	public ResponseBase() {
		// TODO Auto-generated constructor stub
		status = new Status();
		payLoad = new BeanBase();
		payLoadList = new ArrayList<BeanBase>(); 		
	}
	public ResponseBase(Status status, BeanBase payLoad, List<BeanBase> payLoadList) {
		super();
		this.status = status;
		this.payLoad = payLoad;
		this.payLoadList = payLoadList;
	}
	public ResponseBase(BeanBase payLoad, List<BeanBase> payLoadList) {
		super();
		this.status = new Status(200, "", "SUCCESS");
		this.payLoad = payLoad;
		this.payLoadList = payLoadList;
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

	public List<BeanBase> getPayLoadList() {
		return payLoadList;
	}

	public void setPayLoadList(List<BeanBase> payLoadList) {
		this.payLoadList = payLoadList;
	}
}
