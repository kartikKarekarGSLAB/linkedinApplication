package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserPostLikeDAO;
import com.gslab.linkedin.linkedindemo.model.UserPostLike;

public class UserPostLikeDAOImpl implements UserPostLikeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(UserPostLike userPostLike) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userPostLike);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserPostLike alreadyExists(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("from UserPostLike where user_account_id = :userAccountId and user_post_id = :postId");
		query.setInteger("userAccountId", userAccountId);
		query.setInteger("postId", postId);
		UserPostLike userPostLike = (UserPostLike) query.uniqueResult();
		tr.commit();
		session.close();
		return userPostLike;
	}

	@Override
	public List<UserPostLike> findByPostId(Integer postId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPostLike where user_post_id = :postId");
		query.setInteger("postId", postId);
		List<UserPostLike> userPostList = query.list();
		tr.commit();
		session.close();
		return userPostList;
	}

	@Override
	public List<UserPostLike> findByUserAccountIdId(Integer userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPostLike where user_account_id = :userAccountId");
		query.setInteger("userAccountId", userAccountId);
		List<UserPostLike> userPostList = query.list();
		tr.commit();
		session.close();
		return userPostList;
	}

	@Override
	public boolean deleteUserLike(Integer userAccountId, Integer userPostId) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("delete UserPostLike where user_account_id = :userAccountId and user_post_id=:userPostId");
		query.setInteger("userAccountId", userAccountId);
		query.setInteger("userPostId", userPostId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteAllPostLike(Integer userPostId) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete UserPostLike where user_post_id=:userPostId");
		query.setInteger("userPostId", userPostId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter >= 1)
			return true;
		else
			return false;
	}
}
