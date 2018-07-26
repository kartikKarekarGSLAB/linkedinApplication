package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserFollowDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostLikeDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserFollow;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;
import com.gslab.linkedin.linkedindemo.service.UserPostService;

public class UserPostServiceImpl implements UserPostService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserPostDAO userPostDAO;

	@Autowired
	private UserFollowDAO userFollowDAO;
	
	@Autowired
	private UserPostLikeDAO userPostLikeDAO; 

	@Override
	public UserPostVO create(Integer userAccountId, UserPostVO userPostVO) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account id for create post " + userAccountId);
		}

		if (userPostVO.getDescription().isEmpty()) {
			throw new InvalidUserInputException("Please enter some text to create a post.Empty post is not accepted.");
		}
		UserPost userPost = new UserPost(userPostVO.getDescription(), userPostVO.getImageAttachment(), date, date,
				userAccount);
		Integer newPostId = userPostDAO.create(userPost);
		userPostVO.setId(newPostId);
		userPostVO.setDescription(userPost.getDescription());
		userPostVO.setImageAttachment(userPost.getImageAttachment());
		return userPostVO;
	}

	@Override
	public List<BeanBase> findAll(Integer userAccountId) {
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for find all post " + userAccountId);
		}
		List<BeanBase> userPostVOList = new ArrayList<BeanBase>();
		// this will add all post of user.
//			user's own post.
		for (UserPost userPost : userPostDAO.findAll(userAccountId)) {

			UserPostVO post = new UserPostVO(userPost.getId(), userPost.getDescription(), userPost.getImageAttachment(),
					userAccount.getUsername());
			userPostVOList.add(post);
		}

//			user's following member's post.
		for (UserFollow userFollow : userFollowDAO.getFollowingList(userAccountId)) {

			UserAccount followingUserAccount = userAccountDAO.findById(userFollow.getFollowingUserId());

			for (UserPost userPost : userPostDAO.findAll(userFollow.getFollowingUserId())) {

				UserPostVO post = new UserPostVO(userPost.getId(), userPost.getDescription(),
						userPost.getImageAttachment(), followingUserAccount.getUsername());
				userPostVOList.add(post);
			}
		}

//			user's all share post.
		for (UserPost userPost : userPostDAO.findAllShare(userAccountId)) {

			UserAccount postUserAccount = userAccountDAO.findById(userPost.getUserAccount().getId());

			UserPostVO post = new UserPostVO(userPost.getId(), userPost.getDescription(), userPost.getImageAttachment(),
					postUserAccount.getUsername());
			userPostVOList.add(post);
		}

		return userPostVOList;
	}

	@Override
	public UserPostVO update(Integer userAccountId, Integer userPostId, UserPostVO userPostVO) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user account number for post update opertaion " + userAccountId);
		}
		if (userPostVO.getDescription() != null && userPostVO.getDescription().isEmpty()) {
			throw new InvalidUserInputException("Empty description for update post operation.");
		}
		UserPost aboutToUpdateUserPost = new UserPost(userPostVO.getDescription(), userPostVO.getImageAttachment(), date);
		UserPost updatedUserPost = userPostDAO.update(userAccountId, userPostId, aboutToUpdateUserPost);
		if (updatedUserPost == null) {
			throw new CRUDOperationFailureException("Fail to update user post");
		}
		userPostVO.setId(updatedUserPost.getId());
		userPostVO.setDescription(updatedUserPost.getDescription());
		userPostVO.setImageAttachment(updatedUserPost.getImageAttachment());
		userPostVO.setAuthorName(existingUserAccount.getUsername());
		return userPostVO;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer userPostId) {
		// TODO Auto-generated method stub
		userPostLikeDAO.deleteAllPostLike(userPostId);
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			return userPostDAO.delete(userAccountId, userPostId);
		} else {
			throw new InvalidUserInputException("Invalid user account number "+userAccountId+" for delete post operation.");
		}
	}

	@Override
	public UserPostVO findByUserAccountIdAndUserPostId(Integer userAccountId, Integer userPostId) {
		// TODO Auto-generated method stub
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for find post " + userAccountId);
		}

		UserPost existingUserPost = userPostDAO.findByUserAccountIdAndUserPostId(userAccountId, userPostId);
		if (existingUserPost == null) {
			throw new InvalidUserInputException(
					"Invalid user account number " + userAccountId + " or postid " + userPostId + " for post");
		}
		UserPostVO userPostVO = new UserPostVO(existingUserPost.getId(), existingUserPost.getDescription(),
				existingUserPost.getImageAttachment(), existingUserAccount.getUsername());
		return userPostVO;
	}

	@Override
	public UserPostVO find(Integer userPostId) {
		// TODO Auto-generated method stub
		UserPost existingUserPost = userPostDAO.find(userPostId);
		UserPostVO userPostVO = new UserPostVO();
		userPostVO.setDescription(existingUserPost.getDescription());
		userPostVO.setImageAttachment(existingUserPost.getImageAttachment());
		return userPostVO;
	}

	@Override
	public UserPostVO share(Integer userAccountId, Integer userPostId) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserPostVO userPostVO = new UserPostVO();
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account id for repost/share " + userAccountId);
		}
		UserPost existingUserPost = userPostDAO.find(userPostId);
		if (existingUserPost == null) {
			throw new InvalidUserInputException("Invalid postid for repost/share " + userPostId);
		}
		existingUserPost.setCreatedOn(date);
		existingUserPost.setUpdatedOn(date);
		existingUserPost.setRepostUserId(userAccountId);
		existingUserPost.setRepostPostId(existingUserPost.getId());
		Integer newSharePostId = userPostDAO.create(existingUserPost);

		userPostVO.setId(newSharePostId);
		userPostVO.setDescription(existingUserPost.getDescription());
		userPostVO.setImageAttachment(existingUserPost.getImageAttachment());
		userPostVO.setAuthorName(existingUserAccount.getUsername());
		return userPostVO;
	}

	@Override
	public List<BeanBase> findAllShare(Integer userAccountId) {
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for find all post " + userAccountId);
		}
//			user's all share post.
		List<BeanBase> userSharePostVOList = new ArrayList<BeanBase>();
		for (UserPost sharedUserPost : userPostDAO.findAllShare(userAccountId)) {
			UserAccount postAuthorUserAccount = userAccountDAO.findById(sharedUserPost.getUserAccount().getId());
			UserPostVO post = new UserPostVO(sharedUserPost.getId(), sharedUserPost.getDescription(),
					sharedUserPost.getImageAttachment(), postAuthorUserAccount.getUsername());
			userSharePostVOList.add(post);
		}
		return userSharePostVOList;
	}
}
