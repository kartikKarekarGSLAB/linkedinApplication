package com.gslab.linkedin.linkedindemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;
import com.gslab.linkedin.linkedindemo.service.UserFollowService;

@RestController
@RequestMapping(value = "/users")
public class UserFollowController {
	@Autowired
	private UserFollowService userFollowService;

	@RequestMapping(value = "{userAccountId}/follow", method = RequestMethod.GET)
	public ResponseBase getfollowingUserOrFollowerUserList(@PathVariable(name = "userAccountId") Integer userAccountId,
			@RequestParam(name = "filter") String filter) {
		List<UserFollowVO> userList = new ArrayList<UserFollowVO>();
		if (filter.equalsIgnoreCase("follower")) {
			userList = userFollowService.getFollowerList(userAccountId);
		} else if (filter.equalsIgnoreCase("following")) {
			userList = userFollowService.getFollowingList(userAccountId);
		}
		return new ResponseBase(userList);
	}

	@RequestMapping(value = "{userAccountId}/follow/{followingUserName}", method = RequestMethod.POST)
	public ResponseBase follow(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "followingUserName") String followingUserName) {
		UserFollowVO user = userFollowService.follow(userAccountId, followingUserName);
		return new ResponseBase(user);
	}

	@RequestMapping(value = "{userAccountId}/follow/{followingUserName}", method = RequestMethod.DELETE)
	public ResponseBase unfollow(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "followingUserName") String followingUserName) {
		userFollowService.unfollow(userAccountId, followingUserName);
		return new ResponseBase();
	}
}
