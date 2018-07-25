package com.gslab.linkedin.linkedindemo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "user_account")
public class UserAccount {

	@GenericGenerator(name = "userAccountSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "userAccountSequence"),
			@Parameter(name = "initial_value", value = "5001"), @Parameter(name = "increment_size", value = "1") })
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "userAccountSequenceGenerator")
	private int id;

	@Column(name = "username", length = 20, nullable = false)
	private String username;

	@Column(name = "password", length = 20, nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private UserProfileInfo userProfileInfo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount")
	private Set<UserPost> userPost;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount")
	private Set<UserComment> userComment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount")
	private Set<UserPostLike> userPostLike;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount", fetch = FetchType.LAZY)
	private Set<UserCommentLike> userCommentLike;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount", fetch = FetchType.LAZY)
	private Set<MessageUserAccount> messageUserAccount;

	public Set<MessageUserAccount> getMessageUserAccount() {
		return messageUserAccount;
	}

	public void setMessageUserAccount(Set<MessageUserAccount> messageUserAccount) {
		this.messageUserAccount = messageUserAccount;
	}

	public Set<UserCommentLike> getUserCommentLike() {
		return userCommentLike;
	}

	public void setUserCommentLike(Set<UserCommentLike> userCommentLike) {
		this.userCommentLike = userCommentLike;
	}

	public Set<UserPostLike> getUserPostLike() {
		return userPostLike;
	}

	public void setUserPostLike(Set<UserPostLike> userPostLike) {
		this.userPostLike = userPostLike;
	}

	public Set<UserComment> getUserComment() {
		return userComment;
	}

	public void setUserComment(Set<UserComment> userComment) {
		this.userComment = userComment;
	}

	public Set<UserPost> getUserPost() {
		return userPost;
	}

	public void setUserPost(Set<UserPost> userPost) {
		this.userPost = userPost;
	}

	public UserProfileInfo getUserProfileInfo() {
		return userProfileInfo;
	}

	public void setUserProfileInfo(UserProfileInfo userProfileInfo) {
		this.userProfileInfo = userProfileInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAccount() {
	}

	public UserAccount(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
