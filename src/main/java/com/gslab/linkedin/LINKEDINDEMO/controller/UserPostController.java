package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.Status;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;
import com.gslab.linkedin.linkedindemo.service.UserPostService;

@RestController
@RequestMapping(value = "/post")
public class UserPostController {

	@Autowired
	private UserPostService userPostService;

//	List all post of user
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseBase findAll(@PathVariable(name = "id") Integer userAccountId) {
		List<BeanBase> userPostList = userPostService.findAll(userAccountId);
		return new ResponseBase(userPostList);		
	}
	
//	List all post of user
	@RequestMapping(value = "/share/{id}", method = RequestMethod.GET)
	public ResponseBase findAllShare(@PathVariable(name = "id") Integer userAccountId) {
		List<BeanBase> userPostList = userPostService.findAllShare(userAccountId);
		return new ResponseBase(userPostList);		
	}	

//	List post of user by Id
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.GET)
	public ResponseBase findById(@PathVariable(name = "id") Integer userAccountId,
			 	@PathVariable(name = "postid") Integer userPostId) {
		UserPostVO userPost = userPostService.findById(userAccountId, userPostId);
		return new ResponseBase(userPost);
	}

//	Create post for user
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseBase create(@PathVariable(name = "id") Integer userAccountId, @RequestBody UserPostVO userPostVO) {
		UserPostVO userPost = userPostService.create(userAccountId, userPostVO);
		return new ResponseBase(userPost);
	}
//	Repost  / Share other user post.
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.POST)
	public ResponseBase share(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId) {
		UserPostVO userPost = userPostService.share(userAccountId, userPostId);
		return new ResponseBase(userPost);
	}
	
//	Update post for user
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.PUT)
	public ResponseBase update(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId, @RequestBody UserPostVO userPostVO) {
		UserPostVO userPost = userPostService.update(userAccountId, userPostId, userPostVO);
		return new ResponseBase(userPost);
	}

//	Delete post for user
	@RequestMapping(value = "/{id}/{postid}", method = RequestMethod.DELETE)
	public ResponseBase delete(@PathVariable(name = "id") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId) {
		boolean result =  userPostService.delete(userAccountId, userPostId);
		return new ResponseBase();
	}
}
