package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;
import com.gslab.linkedin.linkedindemo.service.UserFollowService;

@RestController
@RequestMapping(value = "/users")
public class UserFollowController {
	@Autowired
	private UserFollowService userFollowService;

	@RequestMapping(value = "/follow/following/{followerId}", method = RequestMethod.GET)
	public ResponseBase getfollowingUserList(@PathVariable(name = "followerId") Integer followerId) {
		List<BeanBase> userFollowingList = userFollowService.getFollowingList(followerId);
		return new ResponseBase(userFollowingList);
	}
	
	@RequestMapping(value = "/follow/follower/{followingId}", method = RequestMethod.GET)
	public ResponseBase getfollowerUserList(@PathVariable(name = "followingId") Integer followingId) {
		List<BeanBase> userFollowingList = userFollowService.getFollowerList(followingId);
		return new ResponseBase(userFollowingList);
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public ResponseBase followUser(@RequestBody UserFollowVO userFollowVO) {
		UserFollowVO user = userFollowService.create(userFollowVO);
		return new ResponseBase(user);
	}

	@RequestMapping(value = "/follow/{followerId}/{followingId}", method = RequestMethod.DELETE)
	public ResponseBase unfollowUser(@PathVariable(name = "followerId") Integer followerId,
			@PathVariable(name = "followingId") Integer followingId) {
		userFollowService.unfollow(followerId, followingId);
		return new ResponseBase();
	}
}
