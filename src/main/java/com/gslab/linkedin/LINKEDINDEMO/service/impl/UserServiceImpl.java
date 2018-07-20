package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserProfileInfoDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserService;
import com.gslab.linkedin.linkedindemo.util.Response;
import com.gslab.linkedin.linkedindemo.validator.UserValidator;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private UserProfileInfoDAO userProfileInfoDAO;
	@Override
	public Response create(UserVO userVO) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try {
			if(UserValidator.validateUserName(userVO.getUsername())) {
				if (UserValidator.validatePassword(userVO.getPassword())) {
						UserAccount userAccount = new UserAccount();
						userAccount.setUsername(userVO.getUsername());
						userAccount.setPassword(userVO.getPassword());
						
						UserProfileInfo userProfileInfo = new UserProfileInfo();
						userProfileInfo.setProfilePicture(userVO.getProfilePictureUrl());
						userProfileInfo.setEmail(userVO.getEmail());
						userProfileInfo.setCompanyName(userVO.getCompanyName());
						userProfileInfo.setDesignation(userVO.getDesignation());
						userProfileInfo.setUserAccount(userAccount);		
						int newUserId = userProfileInfoDAO.create(userProfileInfo);
						response.setStatusCode(200);
						response.setErrorKey("");
						response.setErrorMessage("");
						response.setPayload("New user created with id : "+newUserId);						
				} else {
					throw new InvalidUserInputException("Invalid password");
				}
			} else {
				throw new InvalidUserInputException("Invalid username");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setStatusCode(500);
			response.setErrorKey(e.toString());
			response.setErrorMessage(e.getMessage());
			response.setPayload(null);
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<UserVO> findAll() {
		// TODO Auto-generated method stub
		List<UserAccount> userAccountList = userAccountDAO.findAll();
		List<UserProfileInfo> userProfileInfoList = userProfileInfoDAO.findAll();
		List<UserVO> userVOlist = new ArrayList<UserVO>();
		int index =0;
		for (UserProfileInfo userProfile : userProfileInfoList) {
			System.out.println(userProfile.getEmail());
			UserVO uservo = new UserVO();
     		uservo.setUsername(userAccountList.get(index).getUsername());
			uservo.setProfilePictureUrl(userProfile.getProfilePicture());
			uservo.setEmail(userProfile.getEmail());
			uservo.setCompanyName(userProfile.getCompanyName());
			uservo.setDesignation(userProfile.getDesignation());
			userVOlist.add(uservo);
			index++;
		}
		return userVOlist;
	}

	@Override
	public UserVO findById(Integer userId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userId);
		UserProfileInfo userProfile = userProfileInfoDAO.findById(userId);
		UserVO uservo = new UserVO();
		uservo.setUsername(userAccount.getUsername());
		uservo.setPassword("***********");
 		uservo.setProfilePictureUrl(userProfile.getProfilePicture());
		uservo.setEmail(userProfile.getEmail());
		uservo.setCompanyName(userProfile.getCompanyName());
		uservo.setDesignation(userProfile.getDesignation());		
		return uservo;
	}

	@Override
	public String update(Integer userId,UserVO userVO) {
		// TODO Auto-generated method stub
		String result = "User updated status : "+false;
		UserProfileInfo userProfileInfo =  new UserProfileInfo();
		userProfileInfo.setProfilePicture(userVO.getProfilePictureUrl());
		userProfileInfo.setEmail(userVO.getEmail());
		userProfileInfo.setCompanyName(userVO.getCompanyName());
		userProfileInfo.setDesignation(userVO.getDesignation());
		UserAccount userAccount =  new UserAccount();
		userAccount.setUsername(userVO.getUsername());
		userAccount.setPassword(userVO.getPassword());
		if( userAccountDAO.update(userId, userAccount) ) {
			result = "User updated status : "+userProfileInfoDAO.update(userId, userProfileInfo);			
		}
		return result;
	}

	@Override
	public String delete(Integer userId) {
		// TODO Auto-generated method stub
		return "User deleted : "+userProfileInfoDAO.delete(userId);
	}
}
