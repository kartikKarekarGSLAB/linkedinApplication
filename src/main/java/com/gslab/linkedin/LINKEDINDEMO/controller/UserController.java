package com.gslab.linkedin.linkedindemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.dao.impl.UserAccountDAOImpl;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserService;

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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseBase readUser(@PathVariable(name = "id") Integer userId) {
		UserVO user = userService.findById(userId);
		ResponseBase responseBase = new ResponseBase();
		responseBase.setStatusCode(200);
		responseBase.setErrorKey("OK");
		responseBase.setErrorMessage("");
		responseBase.setPayLoad(user);
		return responseBase;
	}

	// Method for create user
	@RequestMapping(method = RequestMethod.POST)
	public ResponseBase createUser(@RequestBody UserVO userVO) {
		Integer newIntegerId = userService.create(userVO);
		ResponseBase responseBase = new ResponseBase();
		responseBase.setStatusCode(200);
		responseBase.setErrorKey("OK");
		responseBase.setErrorMessage("USER CREATED SUCCESSFULLY WITH ID: " + newIntegerId);
		return responseBase;
	}

	// Method for create user
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseBase updateUser(@PathVariable(name = "id") Integer userAccountId, @RequestBody UserVO userVO) {
		boolean result = userService.update(userAccountId, userVO);
		Integer newIntegerId = userService.create(userVO);
		ResponseBase responseBase = new ResponseBase();
		responseBase.setStatusCode(200);
		responseBase.setErrorKey("OK");
		responseBase.setErrorMessage("USER UPDATED STATUS :" + result);
		return responseBase;
	}

	// Method for create user
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseBase deleteUser(@PathVariable(name = "id") Integer userAccountId) {
		boolean result = userService.delete(userAccountId);
		ResponseBase responseBase = new ResponseBase();
		responseBase.setStatusCode(200);
		responseBase.setErrorKey("OK");
		responseBase.setErrorMessage("USER UPDATED STATUS :" + result);
		return responseBase;
	}
}
