package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageVO extends BeanBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8430173947514662501L;

	private int id;

	private String message;

	private String type;

	private String receiverUserName;

	private String senderUserName;

	public MessageVO() {
		message = "";
		type = "send";
	}

	public MessageVO(int id, String message, String type, String receiverUserName, String senderUserName) {
		super();
		this.id = id;
		this.message = message;
		this.type = type;
		this.receiverUserName = receiverUserName;
		this.senderUserName = senderUserName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}

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
