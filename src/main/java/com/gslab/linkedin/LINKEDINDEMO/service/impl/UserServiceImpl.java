package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserProfileInfoDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
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
		if(userVO.getUsername() == null || userVO.getPassword() == null) {
			throw new InvalidUserInputException("Empty username/password is not allowed for create profile.");
		}
		if (!UserValidator.validateUserName(userVO.getUsername())) {
			throw new InvalidUserInputException("Invalid username for create profile '" + userVO.getUsername() + "'.It must contain minimum 6 character and max size is 19.combination of  letters, digits, '-' and '_' allowed.");
		}
		if (!UserValidator.validatePassword(userVO.getPassword())) {
			throw new InvalidUserInputException("Invalid password for create profile '" + userVO.getUsername() + "'.It must contain minimum 8 character and max size is 19.It MUST contain 1 capital letter,small letter,digit and one special symbol from @,#,$,%,^,&,+,=");
		}
		if (userVO.getEmail().isEmpty()) {
			throw new InvalidUserInputException("Empty email not accepted for create profile.");
		}
		UserAccount existingUserAccount = userAccountDAO.findByUserName(userVO.getUsername());
		if (existingUserAccount != null) {
			throw new InvalidUserInputException(userVO.getUsername() + " username already exists.");
		}
		UserAccount userAccount = new UserAccount(userVO.getUsername(), userVO.getPassword());
		UserProfileInfo userProfileInfo = new UserProfileInfo(userVO.getProfilePictureUrl(), userVO.getEmail(),
				userVO.getCompanyName(), userVO.getDesignation());
		userProfileInfo.setUserAccount(userAccount);
		Integer newUserId = userProfileInfoDAO.create(userProfileInfo);
		userVO.setId(newUserId);
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
			UserVO uservo = new UserVO(userAccountList.get(index).getId(),userAccountList.get(index).getUsername(),
					userAccountList.get(index).getPassword(), userProfile.getProfilePicture(), userProfile.getEmail(),
					userProfile.getCompanyName(), userProfile.getDesignation());
			userVOlist.add(uservo);
			index++;
		}
		return userVOlist;
	}

	@Override
	public UserVO findById(Integer userAccountId) {
		// TODO Auto-generated method stub
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount == null) {
			throw new InvalidUserInputException("No user account exists with id "+userAccountId);
		}
		UserProfileInfo userProfile = userProfileInfoDAO.findById(userAccountId);
		if (userProfile == null) {
			throw new InvalidUserInputException("No user profile exists with id "+userAccountId);
		}
		UserVO uservo = new UserVO(userAccount.getId(),userAccount.getUsername(), userAccount.getPassword(),
				userProfile.getProfilePicture(), userProfile.getEmail(), userProfile.getCompanyName(),
				userProfile.getDesignation());
		return uservo;
	}

	@Override
	public UserVO update(Integer userId, UserVO userVO) {
		// TODO Auto-generated method stub
		UserProfileInfo userProfileInfo = new UserProfileInfo(userVO.getProfilePictureUrl(), userVO.getEmail(),
				userVO.getCompanyName(), userVO.getDesignation());
		UserAccount userAccount = new UserAccount(userVO.getUsername(), userVO.getPassword());
		userAccount = userAccountDAO.update(userId, userAccount);
		if (userAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user account details update for username \'" + userVO.getUsername() + "\'");
		}
		userProfileInfo = userProfileInfoDAO.update(userId, userProfileInfo);
		if (userProfileInfo == null) {
			throw new InvalidUserInputException(
					"Invalid user account details update for username \'" + userVO.getUsername() + "\'");
		}
		userVO.setUsername(userAccount.getUsername());
		userVO.setPassword(userAccount.getPassword());
		userVO.setProfilePictureUrl(userProfileInfo.getProfilePicture());
		userVO.setEmail(userProfileInfo.getEmail());
		userVO.setCompanyName(userProfileInfo.getCompanyName());
		userVO.setDesignation(userProfileInfo.getDesignation());
		return userVO;
	}

	@Override
	public boolean delete(Integer userId) {
		// TODO Auto-generated method stub
		boolean result = userAccountDAO.delete(userId);
		if (result == false) {
			throw new CRUDOperationFailureException("Fail to delete user with id "+userId);
		}
		return result;
	}
}
