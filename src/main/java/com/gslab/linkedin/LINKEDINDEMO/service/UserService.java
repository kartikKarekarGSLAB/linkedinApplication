package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.util.Response;

public interface UserService {
	public Integer create(UserVO userVO);

	public List<UserVO> findAll();

	public UserVO findById(Integer userId);

	public boolean update(Integer userId, UserVO userVO);

	public boolean delete(Integer userId);

}
