package com.gslab.linkedin.linkedindemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_follow")
public class UserFollow {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	private UserAccount FollowerUserAccount;

	@Column(name = "following_user_id")
	private int FollowingUserId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserAccount getFollowerUserAccount() {
		return FollowerUserAccount;
	}

	public void setFollowerUserAccount(UserAccount followerUserAccount) {
		FollowerUserAccount = followerUserAccount;
	}

	public int getFollowingUserId() {
		return FollowingUserId;
	}

	public void setFollowingUserId(int followingUserId) {
		FollowingUserId = followingUserId;
	}

}
