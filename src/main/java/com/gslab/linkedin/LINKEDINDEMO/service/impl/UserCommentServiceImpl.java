package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserCommentDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserComment;
import com.gslab.linkedin.linkedindemo.model.UserPost;
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
	public Integer create(Integer userAccountId, Integer userPostId, UserCommentVO userCommentVO) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				UserPost userPost = userPostDAO.find(userPostId);
				if (userPost != null) {
					if (userCommentVO.getMessage() != null) {
						UserComment userComment = new UserComment();
						userComment.setMessage(userCommentVO.getMessage());
						userComment.setUserAccount(userAccount);
						userComment.setUserPost(userPost);
						userComment.setCreatedOn(date);
						userComment.setUpdatedOn(date);
						return userCommentDAO.create(userComment);
					} else {
						throw new InvalidUserInputException(
								"Please enter some text in comment. empty comment is not accepted.");
					}

				} else {
					throw new InvalidUserInputException("Invalid post id for comment");
				}
			} else {
				throw new InvalidUserInputException("Invalid user account number for comment");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserCommentVO> findAll(Integer userPostId) {
		// TODO Auto-generated method stub
		try {
			UserPost userPost = userPostDAO.find(userPostId);
			if (userPost != null) {
				List<UserComment> userCommentList = userCommentDAO.findAll(userPostId);
				List<UserCommentVO> userCommentVOList = new ArrayList<UserCommentVO>();
				for (UserComment userComment : userCommentList) {
					UserCommentVO comment = new UserCommentVO();
					comment.setMessage(userComment.getMessage());
					userCommentVOList.add(comment);
				}
				return userCommentVOList;
			} else {
				throw new InvalidUserInputException("Invalid post id for comment");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer commentId) {
		// TODO Auto-generated method stub
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				return userCommentDAO.delete(userAccountId, commentId);
			} else {
				throw new InvalidUserInputException("Invalid user account number for comment delete.");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Integer userAccountId, Integer userCommentId, UserCommentVO userCommentVO) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				if (userCommentVO.getMessage() != null) {
					UserComment userComment = new UserComment();
					userComment.setMessage(userCommentVO.getMessage());
					userComment.setUpdatedOn(date);
					return userCommentDAO.update(userAccountId, userCommentId, userComment);
				} else {
					throw new InvalidUserInputException(
							"Please enter some text in comment. empty comment is not accepted.");
				}
			} else {
				throw new InvalidUserInputException("Invalid user account number for comment");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
