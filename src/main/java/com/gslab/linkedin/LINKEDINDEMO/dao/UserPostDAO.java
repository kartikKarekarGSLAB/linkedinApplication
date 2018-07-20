package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;

public interface UserPostDAO {
	public Integer create(UserPost userPost);
	public UserPost find(Integer postId);
	public UserPost findById(Integer userAccountId,Integer postId);
	public List<UserPost> findAll(Integer userAccountId);
	public boolean update(Integer userAccountId,Integer postId,UserPost userPost);
	public boolean delete(Integer userAccountId,Integer postId);
}
