package com.gslab.linkedin.linkedindemo.dao;

import com.gslab.linkedin.linkedindemo.model.UserCommentLike;

public interface UserCommentLikeDAO {
	public Integer create(UserCommentLike userCommentLike);

	public UserCommentLike alreadyExists(Integer userAccountId, Integer userCommentId);

	public boolean delete(Integer userCommentId);
}
