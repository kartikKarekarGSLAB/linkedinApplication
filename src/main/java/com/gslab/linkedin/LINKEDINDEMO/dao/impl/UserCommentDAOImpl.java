package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserCommentDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserComment;

public class UserCommentDAOImpl implements UserCommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(UserComment userComment) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newCommentId = (int) session.save(userComment);
		tr.commit();
		session.close();
		return newCommentId;
	}

	@Override
	public List<UserComment> findAll(Integer postId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserComment where user_post_id= :postId order by created_on desc");
		query.setInteger("postId", postId);
		List<UserComment> userCommentList = query.list();
		tr.commit();
		session.close();
		return userCommentList;
	}

	@Override
	public UserComment update(Integer userAccountId, Integer commentId, UserComment userComment) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserComment where id= :id");
		query.setInteger("id", commentId);
		UserComment existingUserComment = (UserComment) query.uniqueResult();
		if (existingUserComment == null) {
			throw new InvalidUserInputException(
					"invalid userid " + userAccountId + " or commentid " + commentId + " pass for update");
		}
		query = session.createQuery(
				"update UserComment set message= :message , updated_on = :updated_on where id= :id and user_account_id = :user_account_id");
		// update useremail
		if (!userComment.getMessage().isEmpty()) {
			query.setString("message", userComment.getMessage());
		} else {
			query.setString("message", existingUserComment.getMessage());
		}
		query.setTimestamp("updated_on", userComment.getUpdatedOn());
		query.setInteger("id", commentId);
		query.setInteger("user_account_id", userAccountId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return userComment;
		else
			return null;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer commentId) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("delete from UserComment where id = :id and user_account_id = :user_account_id");
		query.setInteger("id", commentId);
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
	public UserComment findById(Integer userCommentId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserComment where id = :userCommentId");
		query.setInteger("userCommentId", userCommentId);
		UserComment userComment = (UserComment) query.uniqueResult();
		tr.commit();
		session.close();
		return userComment;
	}

}
