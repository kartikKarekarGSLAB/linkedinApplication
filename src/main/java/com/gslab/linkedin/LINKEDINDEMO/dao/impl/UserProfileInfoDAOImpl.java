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
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userProfileInfo);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserProfileInfo findById(Integer userAccountId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserProfileInfo where user_account_id= :user_account_id");
		query.setInteger("user_account_id", userAccountId);
		UserProfileInfo result = (UserProfileInfo) query.uniqueResult();
		if (result != null) {
			query = session.createQuery(
					"update UserProfileInfo set email= :email,company_name= :company_name,designation=:designation where user_account_id= :user_account_id");

			// update email
			if (userProfileInfo.getEmail() != null) {
				query.setString("email", userProfileInfo.getEmail());
			} else {
				query.setString("email", result.getEmail());
				userProfileInfo.setEmail(result.getEmail());
			}

			// update company_name
			if (userProfileInfo.getCompanyName() != null) {
				query.setString("company_name", userProfileInfo.getCompanyName());
			} else {
				query.setString("company_name", result.getCompanyName());
				userProfileInfo.setCompanyName(result.getCompanyName());
			}

			// update designation
			if (userProfileInfo.getDesignation() != null) {
				query.setString("designation", userProfileInfo.getDesignation());
			} else {
				query.setString("designation", result.getDesignation());
				userProfileInfo.setDesignation(result.getDesignation());
			}
			query.setInteger("user_account_id", userAccountId);
			updatedRowCounter = query.executeUpdate();

		} else {
			throw new InvalidUserInputException("invalid user account id pass for update "+userAccountId);
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserProfileInfo");
		List<UserProfileInfo> userProfileInfoList = query.list();
		tr.commit();
		session.close();
		return userProfileInfoList;
	}

}
