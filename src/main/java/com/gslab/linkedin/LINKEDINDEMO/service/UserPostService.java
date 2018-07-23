package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;

public interface UserPostService {
	public UserPostVO create(Integer userAccountId, UserPostVO userPost);

	public UserPostVO find(Integer postId);

	public UserPostVO findById(Integer userAccountId, Integer postId);

	public List<BeanBase> findAll(Integer userAccountId);

	public UserPostVO update(Integer userAccountId, Integer postId, UserPostVO userPostVO);
	
	public UserPostVO share(Integer userAccountId, Integer postId);

	public boolean delete(Integer userAccountId, Integer postId);
}
