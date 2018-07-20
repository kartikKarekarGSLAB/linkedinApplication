package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.UserCommentVO;

public interface UserCommentService {
	public Integer create(Integer userAccountId,Integer userPostId,UserCommentVO userCommentVO);	
	public List<UserCommentVO> findAll(Integer userPostId);
	public boolean delete(Integer userAccountId,Integer commentId);
	public boolean update(Integer userAccountId,Integer userCommentId,UserCommentVO userCommentVO);
}
