package com.gslab.linkedin.linkedindemo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "message")
public class Message {

	@GenericGenerator(name = "userMessageSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "userMessageSequence"),
			@Parameter(name = "initial_value", value = "6000"), @Parameter(name = "increment_size", value = "1") })
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "userMessageSequenceGenerator")
	private int id;

	@Column(name = "message")
	private String message;

	@Column(name = "sender_user_name")
	private String senderUserName;

	@Column(name = "receiver_user_name")
	private String receiverUserName;

	@Column(name = "created_on")
	private Date createdOn;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "message", fetch = FetchType.LAZY)
	private Set<MessageUserAccount> messageUserAccount;

	public Message() {
	}

	public Message(String message, String senderUserName, String receiverUserName, Date createdOn) {
		super();
		this.message = message;
		this.senderUserName = senderUserName;
		this.receiverUserName = receiverUserName;
		this.createdOn = createdOn;
	}

	public Set<MessageUserAccount> getMessageUserAccount() {
		return messageUserAccount;
	}

	public void setMessageUserAccount(Set<MessageUserAccount> messageUserAccount) {
		this.messageUserAccount = messageUserAccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
