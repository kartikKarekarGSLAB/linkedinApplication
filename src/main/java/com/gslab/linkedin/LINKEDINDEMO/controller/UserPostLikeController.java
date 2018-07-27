package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostLikeVO;
import com.gslab.linkedin.linkedindemo.service.UserPostLikeService;

@RestController
@RequestMapping(value = "/users")
public class UserPostLikeController {

	@Autowired
	private UserPostLikeService userPostLikeService;

	@RequestMapping(value = "/posts/{userPostId}/like", method = RequestMethod.GET)
	public ResponseBase readUser(@PathVariable(name = "userPostId") Integer userPostId) {
		List<UserPostLikeVO> userNameList = userPostLikeService.listUserLikedPost(userPostId);
		return new ResponseBase(userNameList);
	}

//	user post like 
	@RequestMapping(value = "/{userAccountId}/posts/{userPostId}/like", method = RequestMethod.POST)
	public ResponseBase like(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userPostId") Integer userPostId) {
		UserPostLikeVO userPostLikeVO = userPostLikeService.create(userAccountId, userPostId);
		return new ResponseBase(userPostLikeVO);
	}

	@RequestMapping(value = "/{userAccountId}/posts/{userPostId}/like", method = RequestMethod.DELETE)
	public ResponseBase unlike(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userPostId") Integer userPostId) {
		userPostLikeService.delete(userAccountId, userPostId);
		return new ResponseBase();
	}

}
