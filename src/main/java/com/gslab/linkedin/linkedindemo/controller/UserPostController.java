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
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;
import com.gslab.linkedin.linkedindemo.service.UserPostService;

@RestController
@RequestMapping(value = "/users")
public class UserPostController {

	@Autowired
	private UserPostService userPostService;

//	Pagination
	@RequestMapping(value = "/{userAccountId}/posts", method = RequestMethod.GET)
	public ResponseBase pagination(@PathVariable(name = "userAccountId") Integer userAccountId,
			@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(name = "batchSize", required = false) Integer batchSize) {
		List<UserPostVO> userPostList = new ArrayList<UserPostVO>();
		if ((pageNumber != null && pageNumber > 0) && (batchSize != null && batchSize > 0)) {
			userPostList = userPostService.pagination(userAccountId, pageNumber, batchSize);
		} else {
			userPostList = userPostService.findAll(userAccountId);
		}
		return new ResponseBase(userPostList);
	}

//	List all post of user
	@RequestMapping(value = "/{userAccountId}/posts/share", method = RequestMethod.GET)
	public ResponseBase findAllShare(@PathVariable(name = "userAccountId") Integer userAccountId) {
		List<UserPostVO> userPostList = new ArrayList<UserPostVO>();
		userPostList = userPostService.findAllShare(userAccountId);
		return new ResponseBase(userPostList);
	}

//	List post of user by Id
	@RequestMapping(value = "/{userAccountId}/posts/{userPostId}", method = RequestMethod.GET)
	public ResponseBase findById(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userPostId") Integer userPostId) {
		UserPostVO userPost = userPostService.findByUserAccountIdAndUserPostId(userAccountId, userPostId);
		return new ResponseBase(userPost);
	}

//	Create post for user
	@RequestMapping(value = "/{userAccountId}/posts", method = RequestMethod.POST)
	public ResponseBase create(@PathVariable(name = "userAccountId") Integer userAccountId,
			@RequestBody UserPostVO userPostVO) {
		UserPostVO userPost = userPostService.create(userAccountId, userPostVO);
		return new ResponseBase(userPost);
	}

//	Repost  / Share other user post.
	@RequestMapping(value = "/{userAccountId}/posts/{shareUserPostId}", method = RequestMethod.POST)
	public ResponseBase share(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "shareUserPostId") Integer shareUserPostId) {
		UserPostVO userPost = userPostService.share(userAccountId, shareUserPostId);
		return new ResponseBase(userPost);
	}

//	Update post for user
	@RequestMapping(value = "/{userAccountId}/posts/{userPostId}", method = RequestMethod.PUT)
	public ResponseBase update(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userPostId") Integer userPostId, @RequestBody UserPostVO userPostVO) {
		UserPostVO userPost = userPostService.update(userAccountId, userPostId, userPostVO);
		return new ResponseBase(userPost);
	}

//	Delete post for user
	@RequestMapping(value = "/{userAccountId}/posts/{userPostId}", method = RequestMethod.DELETE)
	public ResponseBase delete(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "userPostId") Integer userPostId) {
		userPostService.delete(userAccountId, userPostId);
		return new ResponseBase();
	}
}
