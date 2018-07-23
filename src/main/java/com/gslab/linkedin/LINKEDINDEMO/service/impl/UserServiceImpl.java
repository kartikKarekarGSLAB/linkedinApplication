package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserProfileInfoDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;
import com.gslab.linkedin.linkedindemo.model.vo.BeanBase;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserService;
import com.gslab.linkedin.linkedindemo.validator.UserValidator;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private UserProfileInfoDAO userProfileInfoDAO;

	@Override
	public UserVO create(UserVO userVO) {
		if (UserValidator.validateUserName(userVO.getUsername())) {
			if (UserValidator.validatePassword(userVO.getPassword())) {
				if (userVO.getEmail() != null) {
					UserAccount existingUserAccount = userAccountDAO.findByUserName(userVO.getUsername());
					if (existingUserAccount == null) {
						UserAccount userAccount = new UserAccount();
						userAccount.setUsername(userVO.getUsername());
						userAccount.setPassword(userVO.getPassword());
						UserProfileInfo userProfileInfo = new UserProfileInfo();
						userProfileInfo.setProfilePicture(userVO.getProfilePictureUrl());
						userProfileInfo.setEmail(userVO.getEmail());
						userProfileInfo.setCompanyName(userVO.getCompanyName());
						userProfileInfo.setDesignation(userVO.getDesignation());
						userProfileInfo.setUserAccount(userAccount);
						userProfileInfoDAO.create(userProfileInfo);
					} else {
						throw new InvalidUserInputException("username already exists ");// add username
					}
				} else {
					throw new InvalidUserInputException("empty/invalid email");
				}
			} else {
				throw new InvalidUserInputException("Invalid password");
			}
		} else {
			throw new InvalidUserInputException("Invalid username");
		}
		return userVO;
	}

	@Override
	public List<BeanBase> findAll() {
		// TODO Auto-generated method stub
		List<UserAccount> userAccountList = userAccountDAO.findAll();
		List<UserProfileInfo> userProfileInfoList = userProfileInfoDAO.findAll();
		List<BeanBase> userVOlist = new ArrayList<BeanBase>();
		int index = 0;
		for (UserProfileInfo userProfile : userProfileInfoList) {
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
	public UserVO update(Integer userId, UserVO userVO) {
		// TODO Auto-generated method stub
		UserProfileInfo userProfileInfo = new UserProfileInfo();
		userProfileInfo.setProfilePicture(userVO.getProfilePictureUrl());
		userProfileInfo.setEmail(userVO.getEmail());
		userProfileInfo.setCompanyName(userVO.getCompanyName());
		userProfileInfo.setDesignation(userVO.getDesignation());
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(userVO.getUsername());
		userAccount.setPassword(userVO.getPassword());

		userAccount = userAccountDAO.update(userId, userAccount);
		if (userAccount != null) {
			userVO.setUsername(userAccount.getUsername());
			userVO.setPassword("*********");
			userProfileInfo = userProfileInfoDAO.update(userId, userProfileInfo);
			if (userProfileInfo != null) {
				userVO.setProfilePictureUrl(userProfileInfo.getProfilePicture());
				userVO.setEmail(userProfileInfo.getEmail());
				userVO.setCompanyName(userProfileInfo.getCompanyName());
				userVO.setDesignation(userProfileInfo.getDesignation());
			}
		}
		return userVO;
	}

	@Override
	public boolean delete(Integer userId) {
		// TODO Auto-generated method stub
		return userProfileInfoDAO.delete(userId);
	}
}
