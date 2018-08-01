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
@Table(name = "user_post_like")
public class UserPostLike {

	@GenericGenerator(name = "userPostLikeSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "userPostLikeSequence"),
			@Parameter(name = "initial_value", value = "100"), @Parameter(name = "increment_size", value = "1") })
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "userPostLikeSequenceGenerator")
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_post_id")
	private UserPost userPost;

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

	public UserPost getUserPost() {
		return userPost;
	}

	public void setUserPost(UserPost userPost) {
		this.userPost = userPost;
	}

}
