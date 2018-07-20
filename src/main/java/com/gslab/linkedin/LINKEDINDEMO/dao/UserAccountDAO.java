package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.UserAccount;

public interface UserAccountDAO {
	public Integer create(UserAccount userAccount);
	public List<UserAccount> findAll();
	public UserAccount findById(Integer userId);
	public boolean update(Integer userId,UserAccount userAccount);
	public boolean delete(Integer userId);
	
}
