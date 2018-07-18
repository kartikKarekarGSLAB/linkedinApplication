package com.gslab.linkedin.LINKEDINDEMO.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.LINKEDINDEMO.dao.UserAccountDAO;
import com.gslab.linkedin.LINKEDINDEMO.dao.UserDAO;
import com.gslab.linkedin.LINKEDINDEMO.dao.UserProfileInfoDAO;
import com.gslab.linkedin.LINKEDINDEMO.dao.impl.UserDAOImpl;
import com.gslab.linkedin.LINKEDINDEMO.exception.InvalidUserInputException;
import com.gslab.linkedin.LINKEDINDEMO.model.UserAccount;
import com.gslab.linkedin.LINKEDINDEMO.model.UserProfileInfo;
import com.gslab.linkedin.LINKEDINDEMO.model.UserVO;
import com.gslab.linkedin.LINKEDINDEMO.service.UserService;
import com.gslab.linkedin.LINKEDINDEMO.validator.UserValidator;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private UserProfileInfoDAO userProfileInfoDAO;
	@Override
	public Integer create(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserVO> findAll() {
		// TODO Auto-generated method stub
		System.out.println(userAccountDAO.findAll());
		System.out.println(userProfileInfoDAO.findAll());
		UserAccount[] userAccountList = (UserAccount[]) userAccountDAO.findAll().toArray();
		UserProfileInfo[] userProfileInfoList = (UserProfileInfo[]) userProfileInfoDAO.findAll().toArray();
		UserVO[] userVOlist = new UserVO[userAccountList.length];
		for (int i = 0; i < userProfileInfoList.length; i++) {
			userVOlist[i] = new UserVO();
			userVOlist[i].setUsername(userAccountList[i].getUsername());
			userVOlist[i].setPassword(userAccountList[i].getPassword());
			userVOlist[i].setProfilePictureUrl(userProfileInfoList[i].getProfilePicture());
			userVOlist[i].setEmail(userProfileInfoList[i].getEmail());
			userVOlist[i].setCompanyName(userProfileInfoList[i].getCompanyName());
			userVOlist[i].setDesignation(userProfileInfoList[i].getDesignation());
		}
		
		return new ArrayList<UserVO>(Arrays.asList(userVOlist));
	}

	@Override
	public UserVO finaById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO update(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
}
