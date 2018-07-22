package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserComment;

public interface UserCommentDAO {

	public Integer create(UserComment userComment);

	public List<UserComment> findAll(Integer userPost);

	public boolean update(Integer userAccountId, Integer commentId, UserComment userComment);

	public boolean delete(Integer userAccountId, Integer commentId);
}
