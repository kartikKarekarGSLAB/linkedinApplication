package com.gslab.linkedin.linkedindemo.model.vo;

public class MessageVO extends BeanBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8430173947514662501L;
	
	private String message;
	
	private String type;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type.toLowerCase();
	}
	
	
	
}
