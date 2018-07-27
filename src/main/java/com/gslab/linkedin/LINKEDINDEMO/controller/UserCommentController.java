package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentService;

@RestController
@RequestMapping(value = "/users")
public class UserCommentController {

	@Autowired
	private UserCommentService userCommentService;

	@RequestMapping(value = "/posts/{postid}/comment", method = RequestMethod.GET)
	public ResponseBase findAll(@PathVariable(name = "postid") Integer userPostId) {
		List<UserCommentVO> userPostList = userCommentService.findAll(userPostId);
		return new ResponseBase(userPostList);
	}

	@RequestMapping(value = "/{userAccountId}/posts/{postid}/comment", method = RequestMethod.POST)
	public ResponseBase create(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId, @RequestBody UserCommentVO userCommentVO) {
		UserCommentVO comment = userCommentService.create(userAccountId, userPostId, userCommentVO);
		return new ResponseBase(comment);
	}

	@RequestMapping(value = "/{userAccountId}/posts/comment/{commnetid}", method = RequestMethod.PUT)
	public ResponseBase update(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "commnetid") Integer userCommentId, @RequestBody UserCommentVO userCommentVO) {
		UserCommentVO comment = userCommentService.update(userAccountId, userCommentId, userCommentVO);
		return new ResponseBase(comment);
	}

	@RequestMapping(value = "/{userAccountId}/posts/comment/{commnetid}", method = RequestMethod.DELETE)
	public ResponseBase delete(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "commnetid") Integer userCommentId) {
		userCommentService.delete(userAccountId, userCommentId);
		return new ResponseBase();
	}
}
