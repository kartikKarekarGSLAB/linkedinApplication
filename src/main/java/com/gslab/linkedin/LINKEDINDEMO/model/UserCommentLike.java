package com.gslab.linkedin.linkedindemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user_comment_like")
public class UserCommentLike {

	@GenericGenerator(name = "userCommentLikeSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "userCommentLikeSequence"),
			@Parameter(name = "initial_value", value = "1000"), @Parameter(name = "increment_size", value = "1") })
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "userCommentLikeSequenceGenerator")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_comment_id")
	private UserComment userCommnet;

	public UserCommentLike() {
		// TODO Auto-generated constructor stub
	}
	public UserCommentLike(UserAccount userAccount, UserComment comment) {
		this.userAccount=userAccount;
		this.userCommnet=comment;
	}

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

	public UserComment getUserCommnet() {
		return userCommnet;
	}

	public void setUserCommnet(UserComment userCommnet) {
		this.userCommnet = userCommnet;
	}
}	
