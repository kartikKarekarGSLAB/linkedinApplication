package com.gslab.linkedin.LINKEDINDEMO.dao;

import com.gslab.linkedin.LINKEDINDEMO.model.UserPostLike;

public interface UserPostLikeDAO {
	public void create(UserPostLike userPostlike);
	public Integer findAll(Integer userPostId);
	public boolean delete(Integer userAccountId,Integer postId);
}
