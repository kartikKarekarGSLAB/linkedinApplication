package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserComment;

public interface UserCommentDAO {

	public Integer create(UserComment userComment);

	public UserComment findById(Integer userCommentId);

	public List<UserComment> findAll(Integer userPost);

	public UserComment update(Integer userAccountId, Integer commentId, UserComment userComment);

	public boolean delete(Integer userAccountId, Integer commentId);
}
