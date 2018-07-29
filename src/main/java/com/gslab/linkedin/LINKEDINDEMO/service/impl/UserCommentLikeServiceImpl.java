package com.gslab.linkedin.linkedindemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserCommentDAO;
import com.gslab.linkedin.linkedindemo.dao.UserCommentLikeDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserComment;
import com.gslab.linkedin.linkedindemo.model.UserCommentLike;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentLikeVO;
import com.gslab.linkedin.linkedindemo.service.UserCommentLikeService;

public class UserCommentLikeServiceImpl implements UserCommentLikeService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserCommentDAO userCommentDAO;

	@Autowired
	private UserCommentLikeDAO userCommentLikeDAO;

	@Override
	public UserCommentLikeVO create(Integer userAccountId, Integer userCommentId) {
		UserAccount existingUser = userAccountDAO.findById(userAccountId);

		if (existingUser == null) {
			throw new InvalidUserInputException("Invalid user account number for like");
		}

		UserComment existingComment = userCommentDAO.findById(userCommentId);
		if (existingComment == null) {
			throw new InvalidUserInputException("Invalid comment id  for like");
		}

		UserCommentLike userCommentLike = new UserCommentLike(existingComment.getUserAccount(), existingComment);
		Integer resultiNTS = userCommentLikeDAO.create(userCommentLike);
		userCommentLike.setId(resultiNTS);

		return new UserCommentLikeVO(userAccountId, userCommentId);
	}

	@Override
	public boolean delete(Integer userAccountId, Integer userCommentId) {

		UserCommentLike userCommentLike = userCommentLikeDAO.alreadyExists(userAccountId, userCommentId);

		if (userCommentLike == null) {
			throw new InvalidUserInputException("Invalid user account id '" + userAccountId
					+ "' for like or wrong comment id fo  like '" + userCommentId + "'");
		}
		boolean result = userCommentLikeDAO.delete(userCommentId);
		if (result == false) {
			throw new CRUDOperationFailureException("Fail to delete comment like for user account id '" + userAccountId
					+ " with comment id as '" + userCommentId + "'");
		}
		return result;
	}

}
