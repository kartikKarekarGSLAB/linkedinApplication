package com.gslab.linkedin.linkedindemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentService;

@RestController
@RequestMapping(value = "/users")
public class UserCommentController {

	@Autowired
	private UserCommentService userCommentService;

	@RequestMapping(value = "/posts/{userPostId}/comments", method = RequestMethod.GET)
	public ResponseBase findAll(@PathVariable(name = "userPostId") Integer userPostId,
			@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(name = "batchSize", required = false) Integer batchSize) {
		List<UserCommentVO> userPostList = new ArrayList<UserCommentVO>();
		if ((pageNumber != null && pageNumber > 0) && (batchSize != null && batchSize > 0)) {
			userPostList = userCommentService.findAll(userPostId, pageNumber, batchSize);
		} else {
			userPostList = userCommentService.findAll(userPostId, 1, 3);
		}
		return new ResponseBase(userPostList);
	}

	@RequestMapping(value = "/{userAccountId}/posts/{userPostId}/comments", method = RequestMethod.POST)
	public ResponseBase create(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userPostId") Integer userPostId, @RequestBody UserCommentVO userCommentVO) {
		UserCommentVO comment = userCommentService.create(userAccountId, userPostId, userCommentVO);
		return new ResponseBase(comment);
	}

	@RequestMapping(value = "/{userAccountId}/posts/comments/{userCommentId}", method = RequestMethod.PUT)
	public ResponseBase update(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userCommentId") Integer userCommentId, @RequestBody UserCommentVO userCommentVO) {
		UserCommentVO comment = userCommentService.update(userAccountId, userCommentId, userCommentVO);
		return new ResponseBase(comment);
	}

	@RequestMapping(value = "/{userAccountId}/posts/comments/{userCommentId}", method = RequestMethod.DELETE)
	public ResponseBase delete(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userCommentId") Integer userCommentId) {
		userCommentService.delete(userAccountId, userCommentId);
		return new ResponseBase();
	}
}
