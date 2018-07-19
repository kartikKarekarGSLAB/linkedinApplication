package com.gslab.linkedin.LINKEDINDEMO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.LINKEDINDEMO.dao.impl.UserAccountDAOImpl;
import com.gslab.linkedin.LINKEDINDEMO.model.vo.UserVO;
import com.gslab.linkedin.LINKEDINDEMO.service.UserService;
import com.gslab.linkedin.LINKEDINDEMO.util.Response;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountDAOImpl userAccountDAOImpl;
	
	@RequestMapping(value = "/")
	public List<UserVO> diplayAll() {
		return userService.findAll();
	}
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	public UserVO readUser(@PathVariable(name="id") Integer userId) {
		return userService.findById(userId);
	}	
	//	Method for create user
	@RequestMapping(method = RequestMethod.POST)
	public Response createUser(@RequestBody UserVO userVO) {
		return userService.create(userVO).getResponseStat();
	}
	//	Method for create user
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public String updateUser(@PathVariable(name="id") Integer userAccountId,@RequestBody UserVO userVO) {
		String result = userService.update(userAccountId, userVO);
		return "User update:" + result;
	}	

	//	Method for create user
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable(name="id") Integer userAccountId) {
		String result = userService.delete(userAccountId);
		return "User delete :" + result;
	}	
}
