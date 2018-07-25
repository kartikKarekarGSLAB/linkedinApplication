package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;

public interface UserPostDAO {
	public Integer create(UserPost userPost);

	public UserPost find(Integer userPostId);

	public UserPost findByUserAccountIdAndUserPostId(Integer userAccountId, Integer userPostId);

	public List<UserPost> findAll(Integer userAccountId);
	
	public List<UserPost> findAllShare(Integer userAccountId);

	public UserPost update(Integer userAccountId, Integer userPostId, UserPost userPost);

	public boolean delete(Integer userAccountId, Integer userPostId);
}
