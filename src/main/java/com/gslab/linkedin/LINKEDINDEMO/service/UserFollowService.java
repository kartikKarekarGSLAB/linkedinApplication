package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;

public interface UserFollowService {

	public UserFollowVO follow(Integer followerUserAccountId, String followingUserName);

	public boolean unfollow(Integer followerUserAccountId, String followingUserName);

	public List<BeanBase> getFollowingList(Integer followerId);

	public List<BeanBase> getFollowerList(Integer followingId);

}
