package com.gslab.linkedin.LINKEDINDEMO.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.LINKEDINDEMO.dao.UserAccountDAO;
import com.gslab.linkedin.LINKEDINDEMO.dao.UserPostDAO;
import com.gslab.linkedin.LINKEDINDEMO.dao.UserPostLikeDAO;
import com.gslab.linkedin.LINKEDINDEMO.exception.InvalidUserInputException;
import com.gslab.linkedin.LINKEDINDEMO.model.UserAccount;
import com.gslab.linkedin.LINKEDINDEMO.model.UserPost;
import com.gslab.linkedin.LINKEDINDEMO.model.UserPostLike;
import com.gslab.linkedin.LINKEDINDEMO.service.UserPostLikeService;

public class UserPostLikeServiceImpl implements UserPostLikeService {

	@Autowired
	private UserPostLikeDAO userPostLikeDAO;
	
	@Autowired
	private UserAccountDAO userAccountDAO;
	
	@Autowired
	private UserPostDAO userPostDAO;	
	
	@Override
	public void create(Integer userAccountId, Integer userPostId) {
		// TODO Auto-generated method stub
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				UserPost userPost = userPostDAO.findById(userAccountId, userPostId);
				if (userPost != null) {
					UserPostLike userPostLike = new UserPostLike();
					userPostLike.setUserAccount(userAccount);
					userPostLike.setUserPost(userPost);
					userPostLikeDAO.create(userPostLike);
				} else {
					throw new InvalidUserInputException("Invalid user or postid for like the post");
				}				
			} else {
				throw new InvalidUserInputException("Invalid user to like the post.");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Integer findAll(Integer userPostId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				UserPost userPost = userPostDAO.findById(userAccountId, postId);
				if (userPost != null) {
					return userPostLikeDAO.delete(userAccountId,postId);
				} else {
					throw new InvalidUserInputException("Invalid user or postid for like the post");
				}				
			} else {
				throw new InvalidUserInputException("Invalid user to like the post.");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
	}

}
