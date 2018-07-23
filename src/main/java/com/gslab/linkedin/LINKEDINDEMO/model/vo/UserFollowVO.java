package com.gslab.linkedin.linkedindemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserFollowVO extends BeanBase {
	private String followingUserName;
	private String followerUserName;
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
