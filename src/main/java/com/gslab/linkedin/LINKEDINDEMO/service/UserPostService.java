package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;

public interface UserPostService {
	public UserPostVO create(Integer userAccountId, UserPostVO userPostVO);

	public UserPostVO find(Integer userPostId);

	public UserPostVO findByUserAccountIdAndUserPostId(Integer userAccountId, Integer userPostId);

	public List<BeanBase> findAll(Integer userAccountId);
	
	public List<BeanBase> findAllShare(Integer userAccountId);

	public UserPostVO update(Integer userAccountId, Integer userPostId, UserPostVO userPostVO);
	
	public UserPostVO share(Integer userAccountId, Integer userPostId);

	public boolean delete(Integer userAccountId, Integer userPostId);
}
