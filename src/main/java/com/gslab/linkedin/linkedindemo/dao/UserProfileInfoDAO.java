package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;

public interface UserProfileInfoDAO {
	public Integer create(UserProfileInfo userProfileInfo);

	public UserProfileInfo findById(Integer userAccountId);

	public UserProfileInfo findByEmail(String userEmail);

	public List<UserProfileInfo> findByUserName(String username);

	public List<UserProfileInfo> findAll();

	public UserProfileInfo update(Integer userAccountId, UserProfileInfo userProfileInfo);

	public boolean delete(Integer userAccountId);
}
