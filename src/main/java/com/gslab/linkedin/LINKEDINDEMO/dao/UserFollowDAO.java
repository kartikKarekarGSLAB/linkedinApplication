package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserFollow;

public interface UserFollowDAO {
	public Integer create(UserFollow userFollow);
	public UserFollow alreadyFollowing(Integer followerId,Integer followingId);
	public List<UserFollow> getFollowingList(Integer followerId);
	public List<UserFollow> getFollowerList(Integer followingId);
	public boolean unfollow(Integer followerId,Integer followingId);
}
