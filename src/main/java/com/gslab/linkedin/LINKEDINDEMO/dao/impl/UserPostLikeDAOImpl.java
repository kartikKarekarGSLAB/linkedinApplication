package com.gslab.linkedin.LINKEDINDEMO.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.LINKEDINDEMO.dao.UserPostLikeDAO;
import com.gslab.linkedin.LINKEDINDEMO.model.UserPostLike;

public class UserPostLikeDAOImpl implements UserPostLikeDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void create(UserPostLike userPostlike) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(userPostlike);
		tr.commit();
		session.close();
	}

	@Override
	public Integer findAll(Integer userPostId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete from UserPostLike where user_account_id= :user_account_id and user_post_like = :user_post_like");
		query.setInteger("user_account_id", userAccountId);
		query.setInteger("user_post_like", postId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if(updatedRowCounter == 1)
			return true;
		else
			return false;
	}

}
