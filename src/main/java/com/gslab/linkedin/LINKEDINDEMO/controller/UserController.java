package com.gslab.linkedin.LINKEDINDEMO.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.LINKEDINDEMO.dao.impl.UserAccountDAOImpl;
import com.gslab.linkedin.LINKEDINDEMO.dao.impl.UserDAOImpl;
import com.gslab.linkedin.LINKEDINDEMO.model.UserAccount;
import com.gslab.linkedin.LINKEDINDEMO.model.UserVO;
import com.gslab.linkedin.LINKEDINDEMO.service.UserService;
import com.gslab.linkedin.LINKEDINDEMO.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountDAOImpl userAccountDAOImpl;
	
	@RequestMapping(value = "/all")
	public List<UserVO> greet() {
		return userService.findAll();
	}
	@RequestMapping(value = "/{id}",method=RequestMethod.GET)
	public UserAccount readUser(@PathVariable(name="id") Integer userId) {
		return userAccountDAOImpl.findById(userId);
	}	
	//	Method for create user
	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@RequestBody UserVO userVO) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(userVO.getUsername());
		userAccount.setPassword(userVO.getPassword());
		int newUserID = userAccountDAOImpl.create(userAccount);
		return "User details with new id:" + newUserID;
	}
	//	Method for create user
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public String updateUser(@PathVariable(name="id") Integer userAccountId,@RequestBody UserVO userVO) {
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(userVO.getUsername());
		userAccount.setPassword(userVO.getPassword());		
		boolean result = userAccountDAOImpl.update(userAccountId, userAccount);
		return "User update:" + result;
	}	

	//	Method for create user
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable(name="id") Integer userAccountId,@RequestBody UserVO userVO) {
		boolean result = userAccountDAOImpl.delete(userAccountId);
		return "User delete :" + result;
	}	
}
