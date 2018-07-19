package com.gslab.linkedin.LINKEDINDEMO.service;

import java.util.List;

import com.gslab.linkedin.LINKEDINDEMO.model.vo.UserVO;
import com.gslab.linkedin.LINKEDINDEMO.util.Response;

public interface UserService {
	public Response create(UserVO userVO);
	public List<UserVO> findAll();
	public UserVO findById(Integer userId);
	public String update(Integer userId,UserVO userVO);
	public String delete(Integer userId);
	
}
