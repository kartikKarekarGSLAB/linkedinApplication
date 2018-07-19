package com.gslab.linkedin.LINKEDINDEMO.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user_post_like")
public class UserPostLike implements Serializable{

	@Column(name="user_account_id")
	@GeneratedValue(generator="accountgen")
	@GenericGenerator(name = "accountgen", strategy = "foreign", 
	parameters = { @Parameter(name = "property", value = "userAccount") })	
	private int user_account_id;
	
	@Column(name="user_post_id")
	@GeneratedValue(generator="postgen")
	@GenericGenerator(name = "postgen", strategy = "foreign", 
	parameters = { @Parameter(name = "property", value = "userPost") })	
	private int user_post_id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_account_id")
	private UserAccount userAccount;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_post_id")	
	private UserPost userPost;

	public int getUser_account_id() {
		return user_account_id;
	}

	public void setUser_account_id(int user_account_id) {
		this.user_account_id = user_account_id;
	}

	public int getUser_post_id() {
		return user_post_id;
	}

	public void setUser_post_id(int user_post_id) {
		this.user_post_id = user_post_id;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public UserPost getUserPost() {
		return userPost;
	}

	public void setUserPost(UserPost userPost) {
		this.userPost = userPost;
	}
	
	
}
