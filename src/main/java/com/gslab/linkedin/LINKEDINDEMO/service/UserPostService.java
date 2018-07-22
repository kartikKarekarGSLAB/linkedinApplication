package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;

public interface UserPostService {
	public Integer create(Integer userAccountId, UserPostVO userPost);

	public UserPostVO find(Integer postId);

	public UserPostVO findById(Integer userAccountId, Integer postId);

	public List<UserPostVO> findAll(Integer userAccountId);

	public boolean update(Integer userAccountId, Integer postId, UserPostVO userPostVO);

	public boolean delete(Integer userAccountId, Integer postId);
}
