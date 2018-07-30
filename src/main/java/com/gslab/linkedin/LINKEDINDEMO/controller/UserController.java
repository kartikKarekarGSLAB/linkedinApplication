package com.gslab.linkedin.linkedindemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gslab.linkedin.linkedindemo.model.vo.FileUserVO;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Display All records
	@RequestMapping(method = RequestMethod.GET)
	public ResponseBase diplayAll(@RequestParam(name = "searchedQuery") String searchedQuery) {
		List<UserVO> userList = new ArrayList<UserVO>();
		if (searchedQuery.equalsIgnoreCase("all")) {
			userList = userService.findAll();
		} else {
			userList = userService.search(searchedQuery);
		}
		return new ResponseBase(userList);
	}

	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResponseBase createUserProfile(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, @RequestParam(name = "email") String email,
			@RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "designation") String designation,
			@RequestParam(name = "profilePicture") MultipartFile profilePicture) {
		FileUserVO newUserProfile = new FileUserVO(username, password, profilePicture, email, companyName, designation);
		FileUserVO createdUserProfile = userService.createWithFile(newUserProfile);
		return new ResponseBase(createdUserProfile);
	}

	// Display single record
	@RequestMapping(value = "/{userAccountId}", method = RequestMethod.GET)
	public ResponseBase readUser(@PathVariable(name = "userAccountId") Integer userAccountId) {
		UserVO user = userService.findById(userAccountId);
		return new ResponseBase(user);
	}

	// Method for create user
	@RequestMapping(method = RequestMethod.POST)
	public ResponseBase createUser(@RequestBody UserVO userVO) {
		UserVO user = userService.create(userVO);
		return new ResponseBase(user);
	}

	// Method for update user
	@RequestMapping(value = "/{userAccountId}", method = RequestMethod.PUT)
	public ResponseBase updateUser(@PathVariable(name = "userAccountId") Integer userAccountId,
			@RequestBody UserVO userVO) {
		UserVO user = userService.update(userAccountId, userVO);
		return new ResponseBase(user);
	}

	// Method for delete user
	@RequestMapping(value = "/{userAccountId}", method = RequestMethod.DELETE)
	public ResponseBase deleteUser(@PathVariable(name = "userAccountId") Integer userAccountId) {
		userService.delete(userAccountId);
		return new ResponseBase();
	}
}
