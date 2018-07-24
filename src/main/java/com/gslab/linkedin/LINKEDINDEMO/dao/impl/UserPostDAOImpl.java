package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.UserPostDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.UserPostVO;

public class UserPostDAOImpl implements UserPostDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserPost create(UserPost userPost) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newPostId = (int) session.save(userPost);
		tr.commit();
		session.close();
		return userPost;
	}

	@Override
	public List<UserPost> findAll(Integer userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("from UserPost where user_account_id = :user_account_id order by updated_on desc");
		query.setInteger("user_account_id", userAccountId);
		List<UserPost> userPostList = query.list();
		tr.commit();
		session.close();
		return userPostList;
	}

	@Override
	public UserPost update(Integer userAccountId, Integer postId, UserPost userPost) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPost where id= :id and user_account_id = :user_account_id");
		query.setInteger("id", postId);
		query.setInteger("user_account_id", userAccountId);
		UserPost result = (UserPost) query.uniqueResult();
		if (result != null) {
			query = session.createQuery(
					"update UserPost set description= :description,image_attachment= :image_attachment,updated_on= :updatedOn where id= :postId");

			// update user post description.
			if (userPost.getDescription() != null) {
				query.setString("description", userPost.getDescription());
			} else {
				query.setString("description", result.getDescription());
				userPost.setDescription(result.getDescription());
			}
			// update user image attachment.
			if (userPost.getImageAttachment() != null) {
				query.setString("image_attachment", userPost.getImageAttachment());
			} else {
				query.setString("image_attachment", result.getImageAttachment());
				userPost.setImageAttachment(result.getImageAttachment());
			}
			query.setTimestamp("updatedOn", userPost.getUpdatedOn());
			query.setInteger("postId", postId);
			updatedRowCounter = query.executeUpdate();
			tr.commit();
			session.close();

		} else {
			throw new InvalidUserInputException("Trying to update invalid post.(wrong user / wrong post id)");
		}
		if (updatedRowCounter == 1)
			return userPost;
		else
			return null;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPost where id= :id and user_account_id = :user_account_id");
		query.setInteger("id", postId);
		query.setInteger("user_account_id", userAccountId);

		UserPost result = (UserPost) query.uniqueResult();
		if (result != null) {
			tr = session.beginTransaction();
			query = session.createQuery("delete from UserPost where id= :id");
			query.setInteger("id", postId);
			updatedRowCounter = query.executeUpdate();
			tr.commit();
			session.close();
		} else {
			throw new InvalidUserInputException("Trying to delete invalid post.(wrong user / wrong post id)");
		}
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public UserPost findById(Integer userAccountId, Integer postId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPost where id= :id and user_account_id = :user_account_id");
		query.setInteger("id", postId);
		query.setInteger("user_account_id", userAccountId);
		UserPost result = (UserPost) query.uniqueResult();
		tr.commit();
		session.close();
		if (result != null) {
			return result;
		} else {
			throw new InvalidUserInputException("Trying to read invalid post.(wrong user / wrong post id)");
		}
	}

	@Override
	public UserPost find(Integer postId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPost where id= :id");
		query.setInteger("id", postId);
		UserPost result = (UserPost) query.uniqueResult();
		tr.commit();
		session.close();
		if (result != null) {
			return result;
		} else {
			throw new InvalidUserInputException("Trying to read invalid post.(wrong user / wrong post id)");
		}
	}

	@Override
	public List<UserPost> findAllShare(Integer userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("from UserPost where repost_user_id = :repostUserId order by updated_on desc");
		query.setInteger("repostUserId", userAccountId);
		List<UserPost> userPostList = query.list();
		tr.commit();
		session.close();
		return userPostList;
	}

}
