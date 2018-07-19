package com.gslab.linkedin.LINKEDINDEMO.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.LINKEDINDEMO.dao.UserAccountDAO;
import com.gslab.linkedin.LINKEDINDEMO.dao.UserPostDAO;
import com.gslab.linkedin.LINKEDINDEMO.exception.InvalidUserInputException;
import com.gslab.linkedin.LINKEDINDEMO.model.UserAccount;
import com.gslab.linkedin.LINKEDINDEMO.model.UserPost;
import com.gslab.linkedin.LINKEDINDEMO.model.vo.UserPostVO;
import com.gslab.linkedin.LINKEDINDEMO.service.UserPostService;

public class UserPostServiceImpl implements UserPostService{

	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private UserPostDAO userPostDAO;
	@Override
	public Integer create(Integer userAccountId, UserPostVO userPostVO) {
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				if (userPostVO.getDescription() != null) {
					UserPost userPost = new UserPost();
					userPost.setDescription(userPostVO.getDescription());
					userPost.setImageAttachment(userPostVO.getImageAttachment());
					userPost.setUserAccount(userAccount);
					return userPostDAO.create(userPost);
				} else {
					throw new InvalidUserInputException("Please enter some text in post. empyt post is not accepted.");
				}
			} else {
				throw new InvalidUserInputException("Invalid user account number for post");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<UserPostVO> findAll(Integer userAccountId) {
		// TODO Auto-generated method stub
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				List<UserPostVO> userPostVOList = new ArrayList<UserPostVO>();
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
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean update(Integer userAccountId,Integer postId, UserPostVO userPostVO) {
		// TODO Auto-generated method stub
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				if (userPostVO.getDescription() != null) {
					UserPost userPost = new UserPost();
					userPost.setDescription(userPostVO.getDescription());
					userPost.setImageAttachment(userPostVO.getImageAttachment());
					return userPostDAO.update(userAccountId,postId, userPost);
				} else {
					throw new InvalidUserInputException("Empty description for update post operation.");
				}
			} else {
				throw new InvalidUserInputException("Invalid user account number for post update opertaion.");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean delete(Integer userAccountId,Integer postId) {
		// TODO Auto-generated method stub
		try {
			UserAccount userAccount = userAccountDAO.findById(userAccountId);
			if (userAccount != null) {
				return userPostDAO.delete(userAccountId,postId);
			} else {
				throw new InvalidUserInputException("Invalid user account number for delete post operation.");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public UserPostVO findById(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		try {
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
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
