package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserFollowDAO;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserFollow;

public class UserFollowDAOImpl implements UserFollowDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Integer create(UserFollow userFollow) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(userFollow);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public UserFollow alreadyFollowing(Integer followerId, Integer followingId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserFollow where following_user_id = :followingId and follower_user_account_id = :followerId");
		query.setInteger("followerId", followerId);
		query.setInteger("followingId", followingId);
		UserFollow userFollow = (UserFollow) query.uniqueResult();
		tr.commit();
		session.close();
		return userFollow;
	}

	@Override
	public List<UserFollow> getFollowingList(Integer followerId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserFollow where follower_user_account_id = :followerId");
		query.setInteger("followerId", followerId);
		List<UserFollow> userFollowerList = query.list();
		tr.commit();
		session.close();
		return userFollowerList;
	}

	@Override
	public List<UserFollow> getFollowerList(Integer followingId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserFollow where following_user_id = :followingId");
		query.setInteger("followingId", followingId);
		List<UserFollow> userFollowerList = query.list();
		tr.commit();
		session.close();
		return userFollowerList;
	}

	@Override
	public boolean unfollow(Integer followerId, Integer followingId) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete from UserFollow where following_user_id = :followingId and follower_user_account_id = :followerId");
		query.setInteger("followerId", followerId);
		query.setInteger("followingId", followingId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

}
