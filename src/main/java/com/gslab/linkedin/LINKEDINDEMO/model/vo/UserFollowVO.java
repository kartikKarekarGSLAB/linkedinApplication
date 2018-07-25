package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserFollowVO extends BeanBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8663747098374659193L;
	private int id;
	private String followingUserName;
	private String followerUserName;

	public UserFollowVO() {
	}

	public UserFollowVO(int id, String followingUserName, String followerUserName) {
		super();
		this.id = id;
		this.followingUserName = followingUserName;
		this.followerUserName = followerUserName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFollowingUserName() {
		return followingUserName;
	}

	public void setFollowingUserName(String followingUserName) {
		this.followingUserName = followingUserName;
	}

	public String getFollowerUserName() {
		return followerUserName;
	}

	public void setFollowerUserName(String followerUserName) {
		this.followerUserName = followerUserName;
	}

}
