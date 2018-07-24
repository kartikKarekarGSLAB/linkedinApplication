package com.gslab.linkedin.linkedindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.service.UserCommentLikeService;

@RestController
@RequestMapping(value = "/commentlike")
public class UserCommentLikeController {
	
	@Autowired
	private UserCommentLikeService userCommentLikeService; 
	
	@RequestMapping(value = "/{userAccountId}/{userComentId}", method = RequestMethod.POST)
	public ResponseBase like(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userComentId") Integer userComentId) {
		;
		return new ResponseBase(userCommentLikeService.create(userAccountId, userComentId));
	}
}
