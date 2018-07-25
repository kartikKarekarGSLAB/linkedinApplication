package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserPostLike;

public interface UserPostLikeDAO {
	public Integer create(UserPostLike userPostLike);
	public UserPostLike alreadyExists(Integer userAccountId,Integer postId);
	public List<UserPostLike> findByPostId(Integer postId);
	public List<UserPostLike> findByUserAccountIdId(Integer userAccountId);
	public boolean deleteUserLike(Integer userAccountId,Integer postId);
	public boolean deleteAllPostLike(Integer postId);
	
}
