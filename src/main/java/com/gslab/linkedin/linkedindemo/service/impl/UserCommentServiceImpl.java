package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserCommentDAO;
import com.gslab.linkedin.linkedindemo.dao.UserCommentLikeDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserComment;
import com.gslab.linkedin.linkedindemo.model.UserCommentLike;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentLikeVO;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentService;

public class UserCommentServiceImpl implements UserCommentService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserPostDAO userPostDAO;

	@Autowired
	private UserCommentDAO userCommentDAO;

	@Autowired
	private UserCommentLikeDAO userCommentLikeDAO;

	@Override
	public UserCommentVO create(Integer userAccountId, Integer userPostId, UserCommentVO userCommentVO) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();

		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for comment " + userAccountId);
		}
		UserPost existingUserPost = userPostDAO.find(userPostId);
		if (existingUserPost == null) {
			throw new InvalidUserInputException("Invalid post id for comment" + userPostId);
		}
		if (userCommentVO.getMessage().isEmpty()) {
			throw new InvalidUserInputException(
					"Please enter some text in comment. empty comment is not accepted for update/create.");
		}
		UserComment userComment = new UserComment();
		userComment.setMessage(userCommentVO.getMessage());
		userComment.setUserAccount(existingUserAccount);
		existingUserPost.setUserAccount(existingUserAccount);
		userComment.setUserPost(existingUserPost);
		userComment.setCreatedOn(date);
		userComment.setUpdatedOn(date);
		Integer newCommentId = userCommentDAO.create(userComment);
		userCommentVO.setCommentorUserName(existingUserAccount.getUsername());
		return userCommentVO;
	}

	@Override
	public List<UserCommentVO> findAll(Integer userPostId, Integer pageNumber, Integer batchSize) {
		UserPost userPost = userPostDAO.find(userPostId);
		if (userPost == null) {
			throw new InvalidUserInputException("Invalid post id for listing comments " + userPostId);
		}
		int offset = 0, limit = batchSize;
		if (pageNumber > 0 && batchSize > 0) {
			offset = (pageNumber - 1) * batchSize;
			limit = batchSize;
		}
		System.out.println("value : offset : " + offset + " limit : " + limit);
		List<UserComment> userCommentList = userCommentDAO.findAll(userPostId, limit, offset);
		List<UserCommentVO> userCommentVOList = new ArrayList<UserCommentVO>();
		for (UserComment userComment : userCommentList) {
//			get user comment like counter and list.
			List<UserCommentLike> userCommentLikeList = userCommentLikeDAO.findByCommentId(userComment.getId());
			int userCommentLikeCounter = userCommentLikeList.size();
			List<UserCommentLikeVO> userCommentLikeVOList = new ArrayList<UserCommentLikeVO>();
			for (UserCommentLike userCommentLike : userCommentLikeList) {
				userCommentLikeVOList.add(new UserCommentLikeVO(userCommentLike.getUserAccount().getId(),
						userCommentLike.getUserCommnet().getId()));
			}
			UserCommentVO comment = new UserCommentVO(userComment.getMessage(),
					userComment.getUserAccount().getUsername(), userCommentLikeCounter, userCommentLikeVOList);
			userCommentVOList.add(comment);
		}
		return userCommentVOList;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer commentId) {
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for comment delete." + userAccountId);
		}
		boolean result = userCommentDAO.delete(userAccountId, commentId);
		if (result == false) {
			throw new CRUDOperationFailureException(
					"Fail to delete comment with id " + commentId + " and user id " + userAccountId);
		}
		return result;
	}

	@Override
	public UserCommentVO update(Integer userAccountId, Integer userCommentId, UserCommentVO userCommentVO) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for comment " + userAccountId);
		}
		if (userCommentVO.getMessage().isEmpty()) {
			throw new InvalidUserInputException(
					"Please enter some text in comment. empty comment is not accepted for update/create.");
		}
		UserComment userComment = new UserComment();
		userComment.setMessage(userCommentVO.getMessage());
		userComment.setUpdatedOn(date);
		UserComment updateUserComment = userCommentDAO.update(userAccountId, userCommentId, userComment);
		if (updateUserComment == null) {
			throw new CRUDOperationFailureException("Fail to update comment for id " + userCommentId);
		}
		userCommentVO.setCommentorUserName(updateUserComment.getUserAccount().getUsername());
		return userCommentVO;
	}

}
