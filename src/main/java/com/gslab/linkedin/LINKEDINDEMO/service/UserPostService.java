package com.gslab.linkedin.LINKEDINDEMO.service;

import java.util.List;

import com.gslab.linkedin.LINKEDINDEMO.model.UserPost;
import com.gslab.linkedin.LINKEDINDEMO.model.vo.UserPostVO;

public interface UserPostService {
	public Integer create(Integer userAccountId,UserPostVO userPost);
	public UserPostVO findById(Integer userAccountId, Integer postId);
	public List<UserPostVO> findAll(Integer userAccountId);
	public boolean update(Integer userAccountId,Integer postId,UserPostVO userPostVO);
	public boolean delete(Integer userAccountId,Integer postId);
}
