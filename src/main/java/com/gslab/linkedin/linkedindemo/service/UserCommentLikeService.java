package com.gslab.linkedin.linkedindemo.service;

import com.gslab.linkedin.linkedindemo.model.vo.UserCommentLikeVO;

public interface UserCommentLikeService {
	public UserCommentLikeVO create(Integer userAccountId, Integer userCommentId);

	public boolean delete(Integer userAccountId, Integer userCommentId);
}
