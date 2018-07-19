package com.gslab.linkedin.LINKEDINDEMO.dao;

import java.util.List;

import com.gslab.linkedin.LINKEDINDEMO.model.UserPost;

public interface UserPostDAO {
	public Integer create(UserPost userPost);
	public UserPost findById(Integer userAccountId,Integer postId);
	public List<UserPost> findAll(Integer userAccountId);
	public boolean update(Integer userAccountId,Integer postId,UserPost userPost);
	public boolean delete(Integer userAccountId,Integer postId);
}
