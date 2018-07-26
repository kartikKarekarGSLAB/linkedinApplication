package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostLikeVO;

public interface UserPostLikeService {
	public UserPostLikeVO create(Integer userAccountId, Integer userPostId);

	public List<BeanBase> listUserLikedPost(Integer userPostId);

	public boolean delete(Integer userAccountId, Integer userPostId);
}
