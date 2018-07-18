package com.gslab.linkedin.LINKEDINDEMO.dao;

import java.util.List;

import com.gslab.linkedin.LINKEDINDEMO.model.UserVO;

public interface UserDAO {
	public Integer create(UserVO userVO);
	public List<UserVO> findAll();
	public UserVO finaById(Integer userId);
	public UserVO update(UserVO userVO);
	public String delete(Integer userId);
}
