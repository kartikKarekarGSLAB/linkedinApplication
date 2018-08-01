package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserProfileInfoDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserProfileInfo;

public class UserProfileInfoDAOImpl implements UserProfileInfoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(UserProfileInfo userProfileInfo) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userProfileInfo);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserProfileInfo findById(Integer userAccountId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserProfileInfo where user_account_id= :user_account_id");
		query.setInteger("user_account_id", userAccountId);
		UserProfileInfo userProfileInfo = (UserProfileInfo) query.uniqueResult();
		tr.commit();
		session.close();
		return userProfileInfo;
	}

	@Override
	public UserProfileInfo update(Integer userAccountId, UserProfileInfo userProfileInfo) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserProfileInfo where user_account_id= :user_account_id");
		query.setInteger("user_account_id", userAccountId);
		UserProfileInfo existingUserProfile = (UserProfileInfo) query.uniqueResult();
		if (existingUserProfile != null) {
			query = session.createQuery(
					"update UserProfileInfo set username=:username , email= :email,company_name= :company_name,designation=:designation where user_account_id= :user_account_id");

			if (userProfileInfo.getUsername() != null && !userProfileInfo.getUsername().isEmpty()) {
				query.setString("username", userProfileInfo.getUsername());
			} else {
				query.setString("username", existingUserProfile.getUsername());
				userProfileInfo.setUsername(existingUserProfile.getUsername());
			}

			// update email
			if (userProfileInfo.getEmail() != null && !userProfileInfo.getEmail().isEmpty()) {
				query.setString("email", userProfileInfo.getEmail());
			} else {
				query.setString("email", existingUserProfile.getEmail());
				userProfileInfo.setEmail(existingUserProfile.getEmail());
			}

			// update company_name
			if (userProfileInfo.getCompanyName() != null) {
				query.setString("company_name", userProfileInfo.getCompanyName());
			} else {
				query.setString("company_name", existingUserProfile.getCompanyName());
				userProfileInfo.setCompanyName(existingUserProfile.getCompanyName());
			}

			// update designation
			if (userProfileInfo.getDesignation() != null) {
				query.setString("designation", userProfileInfo.getDesignation());
			} else {
				query.setString("designation", existingUserProfile.getDesignation());
				userProfileInfo.setDesignation(existingUserProfile.getDesignation());
			}
			query.setInteger("user_account_id", userAccountId);
			updatedRowCounter = query.executeUpdate();

		} else {
			throw new InvalidUserInputException("invalid user account id pass for update " + userAccountId);
		}
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return userProfileInfo;
		else
			return null;
	}

	@Override
	public boolean delete(Integer userAccountId) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete from UserProfileInfo where user_account_id= :user_account_id");
		query.setInteger("user_account_id", userAccountId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<UserProfileInfo> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserProfileInfo");
		List<UserProfileInfo> userProfileInfoList = query.list();
		tr.commit();
		session.close();
		return userProfileInfoList;
	}

	@Override
	public List<UserProfileInfo> findByUserName(String username) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("from UserProfileInfo where lower(username) like '%" + username.toLowerCase() + "%' ");
		List<UserProfileInfo> userProfileInfo = query.list();
		tr.commit();
		session.close();
		return userProfileInfo;
	}

	@Override
	public UserProfileInfo findByEmail(String userEmail) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserProfileInfo where email=:userEmail");
		query.setString("userEmail", userEmail);
		UserProfileInfo userProfileInfo = (UserProfileInfo) query.uniqueResult();
		tr.commit();
		session.close();
		return userProfileInfo;
	}

}
