package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserFollowDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserFollow;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;
import com.gslab.linkedin.linkedindemo.service.UserFollowService;

public class UserFollowServiceImpl implements UserFollowService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserFollowDAO userFollowDAO;

	@Override
	public UserFollowVO create(UserFollowVO userFollowVO) {
		// TODO Auto-generated method stub
		if (!userFollowVO.getFollowerUserName().isEmpty() && !userFollowVO.getFollowingUserName().isEmpty()) {
			if (!userFollowVO.getFollowerUserName().equalsIgnoreCase(userFollowVO.getFollowingUserName())) {
				UserAccount followerUserAccount = userAccountDAO.findByUserName(userFollowVO.getFollowerUserName());
				if (followerUserAccount != null) {
					UserAccount followingUserAccount = userAccountDAO
							.findByUserName(userFollowVO.getFollowingUserName());
					if (followingUserAccount != null) {
						
						UserFollow existingUserFollow = userFollowDAO.alreadyFollowing(followerUserAccount.getId(), followingUserAccount.getId());	
						if (existingUserFollow == null) {
							UserFollow userFollow = new UserFollow();
							userFollow.setFollowerUserAccount(followerUserAccount);
							userFollow.setFollowingUserId(followingUserAccount.getId());
							userFollowDAO.create(userFollow);
						} else {
							throw new InvalidUserInputException("already following the same user");
						}
					} else {
						throw new InvalidUserInputException("following username does not exists");
					}
				} else {
					throw new InvalidUserInputException("follower username does not exists");
				}
			} else {
				throw new InvalidUserInputException("invalid request to follow");
			}

		} else {
			throw new InvalidUserInputException("invalid request to follow");
		}
		return userFollowVO;
	}

	@Override
	public List<BeanBase> getFollowingList(Integer followerId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(followerId);
		List<BeanBase> userFollowingVOList = new ArrayList<BeanBase>();
		if (userAccount != null) {
			for (UserFollow userFollow : userFollowDAO.getFollowingList(followerId)) {
				UserFollowVO followingUser = new UserFollowVO();
				String followingUserName = userAccountDAO.findById(userFollow.getFollowingUserId()).getUsername(); 
				followingUser.setFollowingUserName(followingUserName);
				userFollowingVOList.add(followingUser);
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number.");
		}
		return userFollowingVOList;
	}

	@Override
	public List<BeanBase> getFollowerList(Integer followingId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(followingId);
		List<BeanBase> userFollowerVOList = new ArrayList<BeanBase>();
		if (userAccount != null) {
			for (UserFollow userFollow : userFollowDAO.getFollowerList(followingId)) {
				UserFollowVO followingUser = new UserFollowVO();
				String followingUserName = userAccountDAO.findById(userFollow.getFollowerUserAccount().getId()).getUsername(); 
				followingUser.setFollowerUserName(followingUserName);
				userFollowerVOList.add(followingUser);
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number.");
		}
		return userFollowerVOList;
	}

	@Override
	public boolean unfollow(Integer followerId, Integer followingId) {
		// TODO Auto-generated method stub
		UserFollow existingUserFollow = userFollowDAO.alreadyFollowing(followerId, followingId);
		System.out.println(existingUserFollow);
		if (existingUserFollow != null) {
			return userFollowDAO.unfollow(followerId, followingId);
		} else {
			throw new InvalidUserInputException("Invalid request for unfollow");
		}
	}

}
