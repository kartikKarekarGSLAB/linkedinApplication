package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;

public interface UserCommentService {
	public UserCommentVO create(Integer userAccountId, Integer userPostId, UserCommentVO userCommentVO);

	public List<BeanBase> findAll(Integer userPostId);

	public boolean delete(Integer userAccountId, Integer commentId);

	public UserCommentVO update(Integer userAccountId, Integer userCommentId, UserCommentVO userCommentVO);
}
