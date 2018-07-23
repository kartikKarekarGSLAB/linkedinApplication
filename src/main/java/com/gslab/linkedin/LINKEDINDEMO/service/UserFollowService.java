package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;

public interface UserFollowService {
	public UserFollowVO create(UserFollowVO userFollowVO);
	public List<BeanBase> getFollowingList(Integer followerId);
	public List<BeanBase> getFollowerList(Integer followingId);
	public boolean unfollow(Integer followerId, Integer followingId);	
}
