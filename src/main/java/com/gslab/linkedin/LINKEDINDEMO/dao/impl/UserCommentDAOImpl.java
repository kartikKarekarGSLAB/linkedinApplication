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
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newCommentId = (int) session.save(userComment);
		tr.commit();
		session.close();
		return newCommentId;
	}

	@Override
	public List<UserComment> findAll(Integer postId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserComment order by created_on desc");
		List<UserComment> userCommentList = query.list();
		tr.commit();
		session.close();
		return userCommentList;
	}

	@Override
	public boolean update(Integer userAccountId, Integer commentId, UserComment userComment) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserComment where id= :id");
		query.setInteger("id", commentId);
		UserComment result = (UserComment) query.uniqueResult();
		try {
			if (result != null) {
				query = session.createQuery(
						"update UserComment set message= :message , updated_on = :updated_on where id= :id and user_account_id = :user_account_id");

				// update useremail
				if (userComment.getMessage() != null) {
					query.setString("message", userComment.getMessage());
					query.setTimestamp("updated_on", userComment.getUpdatedOn());
				}
				query.setInteger("id", commentId);
				query.setInteger("user_account_id", userAccountId);
				updatedRowCounter = query.executeUpdate();

			} else {
				throw new InvalidUserInputException("invalid userid or commentid pass for update");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer commentId) {
		// TODO Auto-generated method stub
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

}
