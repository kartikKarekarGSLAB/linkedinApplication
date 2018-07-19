package com.gslab.linkedin.LINKEDINDEMO.service;

public interface UserPostLikeService {
	public void create(Integer userAccountId,Integer userPostId);
	public Integer findAll(Integer userPostId);
	public boolean delete(Integer userAccountId,Integer postId);
}
