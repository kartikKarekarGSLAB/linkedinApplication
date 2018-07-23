package com.gslab.linkedin.linkedindemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.dao.impl.UserAccountDAOImpl;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.Status;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserFollowService;
import com.gslab.linkedin.linkedindemo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserFollowService userFollowService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseBase diplayAll() {
		List<BeanBase> userList = userService.findAll();
		return new ResponseBase(new Status(200, "", ""),null,userList);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseBase readUser(@PathVariable(name = "id") Integer userId) {
		UserVO user = userService.findById(userId);
		return new ResponseBase(new Status(200, "", ""),user,null);
	}

	// Method for create user
	@RequestMapping(value= "/follow",method = RequestMethod.POST)
	public ResponseBase followUser(@RequestBody UserFollowVO userFollowVO) {
		UserFollowVO user = userFollowService.create(userFollowVO);
		return new ResponseBase(new Status(200, "", ""),user,null);
	}	
	@RequestMapping(value= "/follow/following/{followerId}",method = RequestMethod.GET)
	public ResponseBase getfollowingUserList(@PathVariable(name="followerId") Integer followerId) {
		List<BeanBase> userFollowingList = userFollowService.getFollowingList(followerId);
		return new ResponseBase(new Status(200, "", ""),null,userFollowingList);
	}
	
	@RequestMapping(value= "/follow/follower/{followingId}",method = RequestMethod.GET)
	public ResponseBase getfollowerUserList(@PathVariable(name="followingId") Integer followingId) {
		List<BeanBase> userFollowingList = userFollowService.getFollowerList(followingId);
		return new ResponseBase(new Status(200, "", ""),null,userFollowingList);
	}
	
	@RequestMapping(value= "/follow/{followerId}/{followingId}",method = RequestMethod.DELETE)
	public ResponseBase unfollowUser(@PathVariable(name="followerId") Integer followerId,@PathVariable(name="followingId") Integer followingId) {
		userFollowService.unfollow(followerId, followingId);
		return new ResponseBase(new Status(200, "", ""),null,null);
	}	
	// Method for create user
	@RequestMapping(method = RequestMethod.POST)
	public ResponseBase createUser(@RequestBody UserVO userVO) {
		UserVO user = userService.create(userVO);
		return new ResponseBase(new Status(200, "", ""),user,null);
	}

	// Method for create user
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseBase updateUser(@PathVariable(name = "id") Integer userAccountId, @RequestBody UserVO userVO) {
		UserVO user = userService.update(userAccountId, userVO);
		return new ResponseBase(new Status(200, "", ""),user,null);
	}

	// Method for create user
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseBase deleteUser(@PathVariable(name = "id") Integer userAccountId) {
		boolean result = userService.delete(userAccountId);
		return new ResponseBase(new Status(200, "", ""),null,null);
	}
}
