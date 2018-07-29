package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserAccount;

public class UserAccountDAOImpl implements UserAccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(UserAccount userAccount) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userAccount);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserAccount findById(Integer userAccountId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount where id= :id");
		query.setInteger("id", userAccountId);
		UserAccount userAccount = (UserAccount) query.uniqueResult();
		tr.commit();
		session.close();
		return userAccount;
	}

	@Override
	public UserAccount findByUserName(String userName) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount where username= :username");
		query.setString("username", userName);
		UserAccount userAccount = (UserAccount) query.uniqueResult();
		tr.commit();
		session.close();
		return userAccount;
	}

	@Override
	public UserAccount update(Integer userAccountId, UserAccount userAccount) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount where id= :id");
		query.setInteger("id", userAccountId);
		UserAccount result = (UserAccount) query.uniqueResult();
		if (result != null) {
			query = session.createQuery("update UserAccount set username= :username,password= :password where id= :id");

			// update user email
			if (userAccount.getUsername() != null && !userAccount.getUsername().isEmpty()) {
				query.setString("username", userAccount.getUsername());
			} else {
				query.setString("username", result.getUsername());
				userAccount.setUsername(result.getUsername());
			}

			// update user password
			if (userAccount.getPassword() != null && !userAccount.getPassword().isEmpty()) {
				query.setString("password", userAccount.getPassword());
			} else {
				query.setString("password", result.getPassword());
				userAccount.setPassword(result.getPassword());
			}
			query.setInteger("id", userAccountId);
			updatedRowCounter = query.executeUpdate();

		} else {
			throw new InvalidUserInputException("User Account not exists for update with id " + userAccountId);
		}
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return userAccount;
		else
			return null;
	}

	@Override
	public boolean delete(Integer userAccountId) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete from UserAccount where id= :id");
		query.setInteger("id", userAccountId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<UserAccount> findAll() {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount");
		List<UserAccount> userAccountList = query.list();
		tr.commit();
		session.close();
		return userAccountList;
	}

}
