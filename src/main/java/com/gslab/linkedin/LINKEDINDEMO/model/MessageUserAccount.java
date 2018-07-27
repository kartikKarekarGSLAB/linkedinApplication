package com.gslab.linkedin.linkedindemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "message_user_account")
public class MessageUserAccount {

	@GenericGenerator(name = "messageUserAccountSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "messageUserAccountSequence"),
			@Parameter(name = "initial_value", value = "200"), @Parameter(name = "increment_size", value = "1") })
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "messageUserAccountSequenceGenerator")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccount userAccount;

	@ManyToOne(cascade = CascadeType.ALL)
	private Message message;

	@Column(name = "type")
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MessageUserAccount() {
	}

	public MessageUserAccount(UserAccount userAccount, Message message, String type) {
		super();
		this.userAccount = userAccount;
		this.message = message;
		this.type = type;
	}

}
