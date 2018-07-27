package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.UserVO;

public interface UserService {
	public UserVO create(UserVO userVO);

	public List<UserVO> findAll();

	public UserVO findById(Integer userId);

	public UserVO update(Integer userId, UserVO userVO);

	public boolean delete(Integer userId);

}
