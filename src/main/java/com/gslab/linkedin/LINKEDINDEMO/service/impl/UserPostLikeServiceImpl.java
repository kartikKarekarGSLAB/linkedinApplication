package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostLikeDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.UserPostLike;
import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserPostLikeService;

public class UserPostLikeServiceImpl implements UserPostLikeService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserPostDAO userPostDAO;

	@Autowired
	private UserPostLikeDAO userPostLikeDAO;

	@Override
	public boolean create(Integer userAccountId, Integer userPostId) {
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			UserPost userPost = userPostDAO.find(userPostId);
			if (userPost != null) {
				if (userPostLikeDAO.alreadyExists(userAccountId, userPostId) == null) {
					UserPostLike userPostLike = new UserPostLike();
					userPostLike.setUserAccount(userAccount);
					userPost.setUserAccount(userAccount);
					userPostLike.setUserPost(userPost);
					userPostLikeDAO.create(userPostLike);
				} else {
					throw new InvalidUserInputException("already liked the post.");
				}
			} else {
				throw new InvalidUserInputException("Invalid post id for like");
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number for like");
		}
		return true;
	}

	@Override
	public List<BeanBase> listUserLikedPost(Integer userPostId) {
		List<UserPostLike> userPostLikeList = userPostLikeDAO.findByPostId(userPostId);
		List<BeanBase> userVOlist = new ArrayList<BeanBase>();
		for (UserPostLike userPostLike : userPostLikeList) {
			UserVO uservo = new UserVO();
			uservo.setUsername(userAccountDAO.findById(userPostLike.getUserAccount().getId()).getUsername());
			userVOlist.add(uservo);
		}
		return userVOlist;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer userPostId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			UserPost userPost = userPostDAO.find(userPostId);
			if (userPost != null) {
				if (userPostLikeDAO.alreadyExists(userAccountId, userPostId) != null) {
					userPostLikeDAO.delete(userAccountId, userPostId);
				} else {
					throw new InvalidUserInputException("No like by you for this post.");
				}
			} else {
				throw new InvalidUserInputException("Invalid post id for like");
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number for like");
		}
		return true;
	}

}
