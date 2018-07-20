package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.UserVO;

public interface UserDAO {
	public Integer create(UserVO userVO);
	public List<UserVO> findAll();
	public UserVO finaById(Integer userId);
	public UserVO update(UserVO userVO);
	public String delete(Integer userId);
}
