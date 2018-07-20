package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentService;

@RestController
@RequestMapping(value="/comment")
public class UserCommentController {
	
	@Autowired
	private UserCommentService userCommentService;
	
	@RequestMapping(value="/{postid}",method=RequestMethod.GET)
	public List<UserCommentVO> findAll(@PathVariable(name="postid") Integer userPostId) {
		return userCommentService.findAll(userPostId);
	}	
	
	@RequestMapping(value="/{accountid}/{postid}",method=RequestMethod.POST)
	public String create(@PathVariable(name="accountid") Integer userAccountId,@PathVariable(name="postid") Integer userPostId,@RequestBody UserCommentVO userCommentVO) {
		return "Comment added with id:"+userCommentService.create(userAccountId, userPostId, userCommentVO);
	}
	
	@RequestMapping(value="/{accountid}/{commnetid}",method=RequestMethod.PUT)
	public String update(@PathVariable(name="accountid") Integer userAccountId,@PathVariable(name="commnetid") Integer userCommentId,@RequestBody UserCommentVO userCommentVO) {
		return "User Comment deleted :"+userCommentService.update(userAccountId, userCommentId, userCommentVO);
	}	
	
	@RequestMapping(value="/{accountid}/{commnetid}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(name="accountid") Integer userAccountId,@PathVariable(name="commnetid") Integer userCommentId) {
		return "User Comment deleted :"+userCommentService.delete(userAccountId, userCommentId);
	}	
}
