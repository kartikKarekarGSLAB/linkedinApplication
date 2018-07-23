package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;
import com.gslab.linkedin.linkedindemo.service.UserPostService;

public class UserPostServiceImpl implements UserPostService {

	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private UserPostDAO userPostDAO;

	@Override
	public UserPostVO create(Integer userAccountId, UserPostVO userPostVO) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			if (userPostVO.getDescription() != null) {
				UserPost userPost = new UserPost();

				userPost.setDescription(userPostVO.getDescription());
				userPost.setImageAttachment(userPostVO.getImageAttachment());
				userPost.setUserAccount(userAccount);
				userPost.setCreatedOn(date);
				userPost.setUpdatedOn(date);

				userPost = userPostDAO.create(userPost);

				if (userPost != null) {
					userPostVO.setDescription(userPost.getDescription());
					userPostVO.setImageAttachment(userPost.getImageAttachment());
					return userPostVO;
				} else {
					throw new CRUDOperationFailureException("Fail to create user post");
				}
			} else {
				throw new InvalidUserInputException("Please enter some text in post. empty post is not accepted.");
			}
		} else {
			throw new InvalidUserInputException("Invalid user account id for post");
		}
	}

	@Override
	public List<BeanBase> findAll(Integer userAccountId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			List<BeanBase> userPostVOList = new ArrayList<BeanBase>();
			for (UserPost userPost : userPostDAO.findAll(userAccountId)) {
				UserPostVO post = new UserPostVO();
				post.setDescription(userPost.getDescription());
				post.setImageAttachment(userPost.getImageAttachment());
				userPostVOList.add(post);
			}
			return userPostVOList;
		} else {
			throw new InvalidUserInputException("Invalid user account number for post");
		}
	}

	@Override
	public UserPostVO update(Integer userAccountId, Integer postId, UserPostVO userPostVO) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			if (userPostVO.getDescription() != null) {
				UserPost userPost = new UserPost();
				userPost.setDescription(userPostVO.getDescription());
				userPost.setImageAttachment(userPostVO.getImageAttachment());
				userPost.setUpdatedOn(date);
				userPost = userPostDAO.update(userAccountId, postId, userPost);
				if (userPost != null) {
					userPostVO.setDescription(userPost.getDescription());
					userPostVO.setImageAttachment(userPost.getImageAttachment());
					return userPostVO;
				} else {
					throw new CRUDOperationFailureException("Fail to update user post");
				}
			} else {
				throw new InvalidUserInputException("Empty description for update post operation.");
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number for post update opertaion.");
		}
	}

	@Override
	public boolean delete(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			return userPostDAO.delete(userAccountId, postId);
		} else {
			throw new InvalidUserInputException("Invalid user account number for delete post operation.");
		}
	}

	@Override
	public UserPostVO findById(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			UserPost userPost = userPostDAO.findById(userAccountId, postId);
			if (userPost != null) {
				UserPostVO userPostVO = new UserPostVO();
				userPostVO.setDescription(userPost.getDescription());
				userPostVO.setImageAttachment(userPost.getImageAttachment());
				return userPostVO;
			} else {
				throw new InvalidUserInputException("Invalid user account number or postid for post");
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number for post");
		}
	}

	@Override
	public UserPostVO find(Integer postId) {
		// TODO Auto-generated method stub
		UserPost userPost = userPostDAO.find(postId);
		UserPostVO userPostVO = new UserPostVO();
		userPostVO.setDescription(userPost.getDescription());
		userPostVO.setImageAttachment(userPost.getImageAttachment());
		return userPostVO;
	}

	@Override
	public UserPostVO share(Integer userAccountId, Integer postId) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserPostVO userPostVO = new UserPostVO();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			UserPost userPost = userPostDAO.find(postId);
			if (userPost != null) {
				userPost.setCreatedOn(date);
				userPost.setUpdatedOn(date);
				userPost.setRepostUserId(userAccountId);
				System.out.println(userPost);
				userPost = userPostDAO.create(userPost);
				if (userPost != null) {
					userPostVO.setDescription(userPost.getDescription());
					userPostVO.setImageAttachment(userPost.getImageAttachment());
				} else {
					throw new CRUDOperationFailureException("Fail to create user post");
				}

			} else {
				throw new InvalidUserInputException("Invalid postid for repost");
			}
		} else {
			throw new InvalidUserInputException("Invalid user account number for repost");
		}
		return userPostVO;
	}
}
