package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserCommentLikeDAO;
import com.gslab.linkedin.linkedindemo.model.UserCommentLike;

public class UserCommentLikeDAOImpl implements UserCommentLikeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer create(UserCommentLike userCommentLike) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userCommentLike);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserCommentLike alreadyExists(Integer userAccountId, Integer userCommentId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserCommentLike where user_account_id = :userAccountId and user_comment_id = :userCommentId");
		query.setInteger("userAccountId", userAccountId);
		query.setInteger("userCommentId", userCommentId);
		UserCommentLike userCommentLike = (UserCommentLike) query.uniqueResult();
		tr.commit();
		session.close();
		return userCommentLike;
	}

	@Override
	public List<UserCommentLike> findByCommentId(Integer commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserCommentLike> findByUserAccountIdId(Integer userAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer userCommentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
