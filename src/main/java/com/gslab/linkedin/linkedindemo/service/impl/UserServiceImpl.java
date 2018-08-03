package com.gslab.linkedin.linkedindemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserProfileInfoDAO;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;
import com.gslab.linkedin.linkedindemo.model.vo.FileUserVO;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.FileStorageService;
import com.gslab.linkedin.linkedindemo.service.UserService;
import com.gslab.linkedin.linkedindemo.validator.UserValidator;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private UserProfileInfoDAO userProfileInfoDAO;

	@Autowired
	private FileStorageService fileStorageService;

	public void validateUserVO(UserVO userVO) {

		if (userVO.getUsername() == null || userVO.getPassword() == null) {
			throw new InvalidUserInputException("Empty username/password is not allowed for create profile.");
		}
		if (!UserValidator.validateUserName(userVO.getUsername())) {
			throw new InvalidUserInputException("Invalid username for create profile '" + userVO.getUsername()
					+ "'.It must contain minimum 6 character and max size is 19.combination of  letters, digits, '-' and '_' allowed.");
		}
		if (!UserValidator.validatePassword(userVO.getPassword())) {
			throw new InvalidUserInputException("Invalid password for create profile '" + userVO.getUsername()
					+ "'.It must contain minimum 8 character and max size is 19.It MUST contain 1 capital letter,small letter,digit and one special symbol from @,#,$,%,^,&,+,=");
		}
		if (userVO.getEmail() == null || userVO.getEmail().isEmpty()) {
			throw new InvalidUserInputException(
					"Empty email not accepted for create profile for user." + userVO.getUsername());
		}

		if (userVO.getEmail().length() >= 50) {
			throw new InvalidUserInputException("Email not accepted for create profile for user." + userVO.getUsername()
					+ " It must contain character's less than 49.");
		}
		if (userVO.getCompanyName() != null && userVO.getCompanyName().length() >= 50) {
			throw new InvalidUserInputException("Company Name not accepted for create profile for user."
					+ userVO.getUsername() + " It must contain character's less than 49.");
		}
		if (userVO.getDesignation() != null && userVO.getDesignation().length() >= 40) {
			throw new InvalidUserInputException("Designation not accepted for create profile for user."
					+ userVO.getUsername() + " It must contain character's less than 39.");
		}
		List<UserProfileInfo> existingUserProfileInfoList = userProfileInfoDAO.findByUserName(userVO.getUsername());
		if (!existingUserProfileInfoList.isEmpty()) {
			throw new InvalidUserInputException(userVO.getUsername() + " username already exists.");
		}
		UserProfileInfo existingUserProfileInfo = userProfileInfoDAO.findByEmail(userVO.getEmail());
		if (existingUserProfileInfo != null) {
			throw new InvalidUserInputException(userVO.getEmail() + "email already exists.");
		}
	}

	@Override
	public UserVO create(UserVO userVO) {

		validateUserVO(userVO); // validation for userVO creation.

		UserAccount userAccount = new UserAccount(userVO.getUsername(), userVO.getPassword());
		UserProfileInfo userProfileInfo = new UserProfileInfo(userVO.getUsername(), userVO.getProfilePictureUrl(),
				userVO.getEmail(), userVO.getCompanyName(), userVO.getDesignation());
		userProfileInfo.setUserAccount(userAccount);
		Integer newUserId = userProfileInfoDAO.create(userProfileInfo);
		userVO.setId(newUserId);
		return userVO;
	}

	@Override
	public List<UserVO> findAll() {
		List<UserProfileInfo> userProfileInfoList = userProfileInfoDAO.findAll();
		List<UserVO> userVOlist = new ArrayList<UserVO>();
		for (UserProfileInfo userProfile : userProfileInfoList) {
			UserVO uservo = new UserVO(userProfile.getUserAccount().getId(), userProfile.getUsername(),
					userProfile.getProfilePicture(), userProfile.getEmail(), userProfile.getCompanyName(),
					userProfile.getDesignation());
			userVOlist.add(uservo);
		}
		return userVOlist;
	}

	@Override
	public UserVO findById(Integer userAccountId) {
		UserProfileInfo userProfile = userProfileInfoDAO.findById(userAccountId);
		if (userProfile == null) {
			throw new InvalidUserInputException("No user profile exists with id " + userAccountId);
		}
		UserVO uservo = new UserVO(userProfile.getUserAccount().getId(), userProfile.getUsername(),
				userProfile.getProfilePicture(), userProfile.getEmail(), userProfile.getCompanyName(),
				userProfile.getDesignation());
		return uservo;
	}

	@Override
	public UserVO update(Integer userId, UserVO userVO) {

		UserProfileInfo userProfileInfo = new UserProfileInfo(userVO.getUsername(), userVO.getProfilePictureUrl(),
				userVO.getEmail(), userVO.getCompanyName(), userVO.getDesignation());
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
		boolean result = userAccountDAO.delete(userId);
		if (result == false) {
			throw new CRUDOperationFailureException("Fail to delete user with id " + userId);
		}
		return result;
	}

	@Override
	public List<UserVO> search(String username) {
		List<UserProfileInfo> userProfileInfoList = userProfileInfoDAO.findByUserName(username);
		if (userProfileInfoList.isEmpty()) {
			throw new InvalidUserInputException(
					"No user profile exists with search query with username as '" + username + "'");
		}
		List<UserVO> userVOlist = new ArrayList<UserVO>();
		for (UserProfileInfo userProfile : userProfileInfoList) {
			UserVO uservo = new UserVO(userProfile.getUserAccount().getId(), userProfile.getUsername(),
					userProfile.getProfilePicture(), userProfile.getEmail(), userProfile.getCompanyName(),
					userProfile.getDesignation());
			userVOlist.add(uservo);
		}
		return userVOlist;
	}

	@Override
	public FileUserVO createWithFile(FileUserVO userVO) {
		if (userVO.getUsername() == null || userVO.getPassword() == null) {
			throw new InvalidUserInputException("Empty username/password is not allowed for create profile.");
		}
		if (!UserValidator.validateUserName(userVO.getUsername())) {
			throw new InvalidUserInputException("Invalid username for create profile '" + userVO.getUsername()
					+ "'.It must contain minimum 6 character and max size is 19.combination of  letters, digits, '-' and '_' allowed.");
		}
		if (!UserValidator.validatePassword(userVO.getPassword())) {
			throw new InvalidUserInputException("Invalid password for create profile '" + userVO.getUsername()
					+ "'.It must contain minimum 8 character and max size is 19.It MUST contain 1 capital letter,small letter,digit and one special symbol from @,#,$,%,^,&,+,=");
		}
		if (userVO.getEmail() == null || userVO.getEmail().isEmpty()) {
			throw new InvalidUserInputException(
					"Empty email not accepted for create profile for user." + userVO.getUsername());
		}
		if (userVO.getEmail().length() >= 50) {
			throw new InvalidUserInputException("Email not accepted for create profile for user." + userVO.getUsername()
					+ " It must contain character's less than 49.");
		}
		if (userVO.getCompanyName() != null && userVO.getCompanyName().length() >= 50) {
			throw new InvalidUserInputException("Company Name not accepted for create profile for user."
					+ userVO.getUsername() + " It must contain character's less than 49.");
		}
		if (userVO.getDesignation() != null && userVO.getDesignation().length() >= 40) {
			throw new InvalidUserInputException("Designation not accepted for create profile for user."
					+ userVO.getUsername() + " It must contain character's less than 39.");
		}
		UserAccount existingUserAccount = userAccountDAO.findByUserName(userVO.getUsername());
		if (existingUserAccount != null) {
			throw new InvalidUserInputException(userVO.getUsername() + " username already exists.");
		}

		String profilePictureURL = fileStorageService.storeFile(userVO.getProfilePicture(), userVO.getUsername());

		UserAccount userAccount = new UserAccount(userVO.getUsername(), userVO.getPassword());
		UserProfileInfo userProfileInfo = new UserProfileInfo(userVO.getUsername(), profilePictureURL,
				userVO.getEmail(), userVO.getCompanyName(), userVO.getDesignation());
		userProfileInfo.setUserAccount(userAccount);
		Integer newUserId = userProfileInfoDAO.create(userProfileInfo);
		userVO.setId(newUserId);
		userVO.setProfilePictureURL(profilePictureURL);
		return userVO;
	}
}
