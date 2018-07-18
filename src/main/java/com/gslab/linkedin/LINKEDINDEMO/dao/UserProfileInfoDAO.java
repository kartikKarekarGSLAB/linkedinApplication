package com.gslab.linkedin.LINKEDINDEMO.dao;

import java.util.List;

import com.gslab.linkedin.LINKEDINDEMO.model.UserProfileInfo;

public interface UserProfileInfoDAO {
	public Integer create(UserProfileInfo userProfileInfo);
	public UserProfileInfo findById(Integer userAccountId);
	public List<UserProfileInfo> findAll();
	public boolean update(Integer userId,UserProfileInfo userProfileInfo);
	public boolean delete(Integer userId);
}
