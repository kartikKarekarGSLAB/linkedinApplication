package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserCommentDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserComment;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentService;

public class UserCommentServiceImpl implements UserCommentService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserPostDAO userPostDAO;

	@Autowired
	private UserCommentDAO userCommentDAO;

	@Override
	public UserCommentVO create(Integer userAccountId, Integer userPostId, UserCommentVO userCommentVO) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for comment "+userAccountId);
		}
		UserPost userPost = userPostDAO.find(userPostId);
		if (userPost == null) {
			throw new InvalidUserInputException("Invalid post id for comment"+userPostId);
		}
		if (userCommentVO.getMessage().isEmpty()) {
			throw new InvalidUserInputException(
					"Please enter some text in comment. empty comment is not accepted.");
		}
		UserComment userComment = new UserComment();
		userComment.setMessage(userCommentVO.getMessage());
		userComment.setUserAccount(userAccount);
		userPost.setUserAccount(userAccount);
		userComment.setUserPost(userPost);
		userComment.setCreatedOn(date);
		userComment.setUpdatedOn(date);
		Integer newCommentId = userCommentDAO.create(userComment);
		return userCommentVO;
	}

	@Override
	public List<BeanBase> findAll(Integer userPostId) {
		// TODO Auto-generated method stub
		UserPost userPost = userPostDAO.find(userPostId);
		if (userPost == null) {
			throw new InvalidUserInputException("Invalid post id for comment " + userPostId);
		}
		List<UserComment> userCommentList = userCommentDAO.findAll(userPostId);
		List<BeanBase> userCommentVOList = new ArrayList<BeanBase>();
		for (UserComment userComment : userCommentList) {
			UserCommentVO comment = new UserCommentVO();
			comment.setMessage(userComment.getMessage());
			userCommentVOList.add(comment);
		}
		return userCommentVOList;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer commentId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for comment delete." + userAccountId);
		}
		return userCommentDAO.delete(userAccountId, commentId);
	}

	@Override
	public UserCommentVO update(Integer userAccountId, Integer userCommentId, UserCommentVO userCommentVO) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for comment");
		}
		if (userCommentVO.getMessage().isEmpty()) {
			throw new InvalidUserInputException(
					"Please enter some text in comment. empty comment is not accepted.");
		}
		UserComment userComment = new UserComment();
		userComment.setMessage(userCommentVO.getMessage());
		userComment.setUpdatedOn(date);
		boolean result = userCommentDAO.update(userAccountId, userCommentId, userComment);
		if (result == false) {
			throw new CRUDOperationFailureException("Fail to update comment for id "+userCommentId);
		}
		return userCommentVO; 
	}

}
