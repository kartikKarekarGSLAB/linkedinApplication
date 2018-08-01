package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserFollowDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserFollow;
import com.gslab.linkedin.linkedindemo.model.vo.UserFollowVO;
import com.gslab.linkedin.linkedindemo.service.UserFollowService;

public class UserFollowServiceImpl implements UserFollowService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserFollowDAO userFollowDAO;

	@Override
	public UserFollowVO follow(Integer followerUserAccountId, String followingUserName) {
		if (followingUserName.isEmpty()) {
			throw new InvalidUserInputException(
					"Please select username for following his/her profile.It Should not be empty.");
		}
		// get follower user account.
		UserAccount followerUserAccount = userAccountDAO.findById(followerUserAccountId);
		if (followerUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account id for follower." + followerUserAccountId);
		}
		if (followingUserName.equalsIgnoreCase(followerUserAccount.getUsername())) {
			throw new InvalidUserInputException(
					"Invalid follow request .Similar follower and following username. " + followingUserName);
		}
		// get following user account.
		UserAccount followingUserAccount = userAccountDAO.findByUserName(followingUserName);
		if (followingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user name for following." + followingUserName);
		}
		// check existing follow request.
		UserFollow existingUserFollow = userFollowDAO.alreadyFollowing(followerUserAccount.getId(),
				followingUserAccount.getId());
		if (existingUserFollow != null) {
			throw new InvalidUserInputException(
					followerUserAccount.getUsername() + " already following " + followingUserName);
		}
		UserFollow userFollow = new UserFollow();
		userFollow.setFollowerUserAccount(followerUserAccount);
		userFollow.setFollowingUserId(followingUserAccount.getId());
		Integer newFollowId = userFollowDAO.create(userFollow);
		return new UserFollowVO(newFollowId, followingUserName, followerUserAccount.getUsername());
	}

	@Override
	public boolean unfollow(Integer followerUserAccountId, String followingUserName) {
		if (followingUserName.isEmpty()) {
			throw new InvalidUserInputException(
					"Please select username for unfollowing his/her profile.It Should not be empty.");
		}
		// get follower user account.
		UserAccount followerUserAccount = userAccountDAO.findById(followerUserAccountId);
		if (followerUserAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user account id for follower." + followerUserAccountId + " inside unfollow request");
		}
		if (followingUserName.equalsIgnoreCase(followerUserAccount.getUsername())) {
			throw new InvalidUserInputException(
					"Invalid unfollow request .Similar follower and following username. " + followingUserName);
		}
		// get following user account.
		UserAccount followingUserAccount = userAccountDAO.findByUserName(followingUserName);
		if (followingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user name for following." + followingUserName);
		}
		// check existing follow request.
		UserFollow existingUserFollow = userFollowDAO.alreadyFollowing(followerUserAccount.getId(),
				followingUserAccount.getId());
		if (existingUserFollow == null) {
			throw new InvalidUserInputException(
					followerUserAccount.getUsername() + " is not following " + followingUserName);
		}
		boolean result = userFollowDAO.unfollow(followerUserAccount.getId(), followingUserAccount.getId());
		if (result == false) {
			throw new CRUDOperationFailureException("Fail to unfollow user with follower id " + followerUserAccountId
					+ " and following username " + followingUserName);
		}
		return result;
	}

	@Override
	public List<UserFollowVO> getFollowingList(Integer followerId) {
		List<UserFollowVO> userFollowingVOList = new ArrayList<UserFollowVO>();
		UserAccount existingFollowerUserAccount = userAccountDAO.findById(followerId);
		if (existingFollowerUserAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user account number for finding following user group " + followerId);
		}
		for (UserFollow userFollow : userFollowDAO.getFollowingList(followerId)) {
			String followingUserName = userAccountDAO.findById(userFollow.getFollowingUserId()).getUsername();
			UserFollowVO followingUser = new UserFollowVO(userFollow.getId(), followingUserName,
					existingFollowerUserAccount.getUsername());
			userFollowingVOList.add(followingUser);
		}
		return userFollowingVOList;
	}

	@Override
	public List<UserFollowVO> getFollowerList(Integer followingId) {
		List<UserFollowVO> userFollowerVOList = new ArrayList<UserFollowVO>();
		UserAccount existingUserFollowingAccount = userAccountDAO.findById(followingId);
		if (existingUserFollowingAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for finding follower user group.");
		}
		for (UserFollow userFollower : userFollowDAO.getFollowerList(followingId)) {
			String followerUserName = userAccountDAO.findById(userFollower.getFollowerUserAccount().getId())
					.getUsername();
			UserFollowVO followingUser = new UserFollowVO(userFollower.getId(),
					existingUserFollowingAccount.getUsername(), followerUserName);
			userFollowerVOList.add(followingUser);
		}
		return userFollowerVOList;
	}
}
