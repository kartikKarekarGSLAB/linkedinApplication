package com.gslab.linkedin.linkedindemo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	
	@Column(name = "type")
	private String type;

	@Column(name = "created_on")
	private Date createdOn;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "message_user_account", joinColumns = { @JoinColumn(name = "message_id") }, inverseJoinColumns = { @JoinColumn(name = "user_account_id") })
	private Set<UserAccount> userAccount = new HashSet<UserAccount>(0);

	public Set<UserAccount> getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Set<UserAccount> userAccount) {
		this.userAccount = userAccount;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
