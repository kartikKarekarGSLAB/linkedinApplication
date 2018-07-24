package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;

public interface UserPostLikeService {
	public boolean create(Integer userAccountId, Integer userPostId);

	public List<BeanBase> listUserLikedPost(Integer userPostId);

	public boolean delete(Integer userAccountId, Integer userPostId);
}
