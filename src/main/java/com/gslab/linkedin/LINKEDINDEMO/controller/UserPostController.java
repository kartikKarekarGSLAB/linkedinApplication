package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;
import com.gslab.linkedin.linkedindemo.service.UserPostService;

@RestController
@RequestMapping(value = "/post")
public class UserPostController {

	@Autowired
	private UserPostService userPostService;

//	List all post of user
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public List<UserPostVO> findAll(@PathVariable(name = "id") Integer userAccountId) {
		return userPostService.findAll(userAccountId);
	}

//	List post of user by Id
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.GET)
	public UserPostVO findById(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId) {
		return userPostService.findById(userAccountId, userPostId);
	}

//	Create post for user
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String createPost(@PathVariable(name = "id") Integer userAccountId, @RequestBody UserPostVO userPostVO) {
		return "User post created :" + userPostService.create(userAccountId, userPostVO);
	}

//	Create post for user
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.PUT)
	public String updatePost(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId, @RequestBody UserPostVO userPostVO) {
		return "User post updated :" + userPostService.update(userAccountId, userPostId, userPostVO);
	}

//	Create post for user
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.DELETE)
	public String deletePost(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId) {
		return "User post updated :" + userPostService.delete(userAccountId, userPostId);
	}
}
