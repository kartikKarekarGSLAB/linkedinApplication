package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.service.UserPostLikeService;

@RestController
@RequestMapping(value = "/postlike")
public class UserPostLikeController {

	@Autowired
	private UserPostLikeService userPostLikeService;
	
//	user post like 
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.POST)
	public ResponseBase like(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId) {
		userPostLikeService.create(userAccountId, userPostId);
		return new ResponseBase();
	}	
	@RequestMapping(value = "/{userPostId}", method = RequestMethod.GET)
	public ResponseBase readUser(@PathVariable(name = "userPostId") Integer userPostId) {
		List<BeanBase> userNameList = userPostLikeService.listUserLikedPost(userPostId);
		return new ResponseBase(userNameList);
	}
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.DELETE)
	public ResponseBase unlike(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId) {
		userPostLikeService.delete(userAccountId, userPostId);
		return new ResponseBase();
	}	
	
}
