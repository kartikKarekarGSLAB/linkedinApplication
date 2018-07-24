package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import javax.swing.text.rtf.RTFEditorKit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentService;

@RestController
@RequestMapping(value = "/comment")
public class UserCommentController {

	@Autowired
	private UserCommentService userCommentService;

	@RequestMapping(value = "/{postid}", method = RequestMethod.GET)
	public ResponseBase findAll(@PathVariable(name = "postid") Integer userPostId) {
		List<BeanBase> userPostList = userCommentService.findAll(userPostId); 
		return new ResponseBase(userPostList);
	}

	@RequestMapping(value = "/{accountid}/{postid}", method = RequestMethod.POST)
	public ResponseBase create(@PathVariable(name = "accountid") Integer userAccountId,
			@PathVariable(name = "postid") Integer userPostId, @RequestBody UserCommentVO userCommentVO) {
		UserCommentVO comment = userCommentService.create(userAccountId, userPostId, userCommentVO);
		return new ResponseBase(comment);
	}

	@RequestMapping(value = "/{accountid}/{commnetid}", method = RequestMethod.PUT)
	public ResponseBase update(@PathVariable(name = "accountid") Integer userAccountId,
			@PathVariable(name = "commnetid") Integer userCommentId, @RequestBody UserCommentVO userCommentVO) {
		UserCommentVO comment = userCommentService.update(userAccountId, userCommentId, userCommentVO);
		return new ResponseBase(comment); 
	}

	@RequestMapping(value = "/{accountid}/{commnetid}", method = RequestMethod.DELETE)
	public ResponseBase delete(@PathVariable(name = "accountid") Integer userAccountId,
			@PathVariable(name = "commnetid") Integer userCommentId) {
		userCommentService.delete(userAccountId, userCommentId);
		return new ResponseBase();
	}
}
