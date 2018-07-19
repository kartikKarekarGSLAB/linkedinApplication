package com.gslab.linkedin.LINKEDINDEMO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.LINKEDINDEMO.service.UserPostLikeService;

@RestController
@RequestMapping(value="/postlike")
public class UserPostLikeController {
	
	@Autowired
	private UserPostLikeService userPostLikeService;
	
	@RequestMapping(value="/{accountId}/{postId}",method=RequestMethod.POST)
	public void create(@PathVariable(name="accountId")Integer userAccountId, @PathVariable(name="postId")Integer userPostId) {
		userPostLikeService.create(userAccountId, userPostId);
	}
	
	@RequestMapping(value="/{accountId}/{postId}",method=RequestMethod.POST)
	public String  delete(@PathVariable(name="accountId")Integer userAccountId, @PathVariable(name="postId")Integer userPostId) {
		return "POst like removed : "+userPostLikeService.delete(userAccountId, userPostId);
	}	
}
