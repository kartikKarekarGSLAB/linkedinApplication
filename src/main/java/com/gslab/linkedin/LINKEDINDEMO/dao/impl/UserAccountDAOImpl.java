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
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userAccount);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserAccount findById(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount where id= :id");
		query.setInteger("id", userId);
		UserAccount userAccount = (UserAccount) query.uniqueResult();
		tr.commit();
		session.close();
		return userAccount;
	}

	@Override
	public UserAccount findByUserName(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount where username= :username");
		query.setString("username", username);
		UserAccount userAccount = (UserAccount) query.uniqueResult();
		tr.commit();
		session.close();
		return userAccount;
	}	
	
	@Override
	public UserAccount update(Integer userId, UserAccount userAccount) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount where id= :id");
		query.setInteger("id", userId);
		UserAccount result = (UserAccount) query.uniqueResult();
		if (result != null) {
			query = session
					.createQuery("update UserAccount set username= :username,password= :password where id= :userId");

			// update useremail
			if (userAccount.getUsername() != null) {
				query.setString("username", userAccount.getUsername());
			} else {
				query.setString("username", result.getUsername());
				userAccount.setUsername(result.getUsername());
			}

			// update user password
			if (userAccount.getPassword() != null) {
				query.setString("password", userAccount.getPassword());
			} else {
				query.setString("password", result.getPassword());
				userAccount.setPassword(result.getPassword());
			}
			query.setInteger("userId", userId);
			updatedRowCounter = query.executeUpdate();

		} else {
			throw new InvalidUserInputException("invalid userid pass for update");
		}
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return userAccount;
		else
			return null;
	}

	@Override
	public boolean delete(Integer userId) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete from UserAccount where id= :id");
		query.setInteger("id", userId);
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
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserAccount");
		List<UserAccount> userAccountList = query.list();
		tr.commit();
		session.close();
		return userAccountList;
	}

}
