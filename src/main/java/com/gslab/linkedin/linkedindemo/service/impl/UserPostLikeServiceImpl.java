package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostLikeDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.UserPostLike;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostLikeVO;
import com.gslab.linkedin.linkedindemo.service.UserPostLikeService;

public class UserPostLikeServiceImpl implements UserPostLikeService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserPostDAO userPostDAO;

	@Autowired
	private UserPostLikeDAO userPostLikeDAO;

	@Override
	public UserPostLikeVO create(Integer userAccountId, Integer userPostId) {
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account number " + userAccountId + " for like");
		}
		UserPost existingUserPost = userPostDAO.find(userPostId);
		if (existingUserPost == null) {
			throw new InvalidUserInputException("Invalid post id " + userPostId + " for like");

		}
		if (userPostLikeDAO.alreadyExists(userAccountId, userPostId) != null) {
			throw new InvalidUserInputException(existingUserAccount.getUsername()
					+ "you already liked the post with id " + existingUserPost.getId());
		}
		UserPostLike userPostLike = new UserPostLike();
		userPostLike.setUserAccount(existingUserAccount);
		existingUserPost.setUserAccount(existingUserAccount);
		userPostLike.setUserPost(existingUserPost);
		Integer newUserPostLikeId = userPostLikeDAO.create(userPostLike);

		return new UserPostLikeVO(newUserPostLikeId, existingUserAccount.getUsername(), userPostId);
	}

	@Override
	public List<UserPostLikeVO> listUserLikedPost(Integer userPostId) {
		List<UserPostLike> userPostLikeList = userPostLikeDAO.findByPostId(userPostId);
		List<UserPostLikeVO> userVOlist = new ArrayList<UserPostLikeVO>();
		for (UserPostLike userPostLike : userPostLikeList) {
			UserPostLikeVO userPostLikeVO = new UserPostLikeVO(userPostLike.getId(),
					userAccountDAO.findById(userPostLike.getUserAccount().getId()).getUsername(), userPostId);
			userVOlist.add(userPostLikeVO);
		}
		return userVOlist;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer userPostId) {
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user account number " + userAccountId + " for like the post with id " + userPostId);
		}
		UserPost existingUserPost = userPostDAO.find(userPostId);

		if (existingUserPost == null) {
			throw new InvalidUserInputException("Invalid post id " + userPostId + " for like by user " + userAccountId);
		}
		if (userPostLikeDAO.alreadyExists(userAccountId, userPostId) == null) {
			throw new InvalidUserInputException("Sorry you didn't liked this post.");
		}
		boolean result = userPostLikeDAO.deleteUserLike(userAccountId, userPostId);
		if (result == false) {
			throw new CRUDOperationFailureException(
					"Fail to delete like for post with id " + userPostId + " for user with id " + userAccountId);
		}
		return result;
	}

}
