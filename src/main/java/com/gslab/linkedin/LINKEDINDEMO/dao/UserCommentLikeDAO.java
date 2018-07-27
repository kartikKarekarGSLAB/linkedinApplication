package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserCommentLike;

public interface UserCommentLikeDAO {
	public Integer create(UserCommentLike userCommentLike);

	public UserCommentLike alreadyExists(Integer userAccountId, Integer userCommentId);

	public List<UserCommentLike> findByCommentId(Integer commentId);

	public List<UserCommentLike> findByUserAccountIdId(Integer userAccountId);

	public boolean delete(Integer userAccountId, Integer userCommentId);
}
