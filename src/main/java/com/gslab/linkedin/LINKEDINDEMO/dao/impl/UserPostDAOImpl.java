package com.gslab.linkedin.LINKEDINDEMO.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.LINKEDINDEMO.dao.UserPostDAO;
import com.gslab.linkedin.LINKEDINDEMO.exception.InvalidUserInputException;
import com.gslab.linkedin.LINKEDINDEMO.model.UserPost;

public class UserPostDAOImpl implements UserPostDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(UserPost userPost) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newPostId = (int) session.save(userPost);
		tr.commit();
		session.close();
		return newPostId;
	}

	@Override
	public List<UserPost> findAll(Integer userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from UserPost where user_account_id = :user_account_id");
		query.setInteger("user_account_id", userAccountId);
		List<UserPost> userPostList = query.list();
		tr.commit();
		session.close();
		return userPostList;
	}

	@Override
	public boolean update(Integer userAccountId, Integer postId, UserPost userPost) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		try {
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			Query query = session.createQuery("from UserPost where id= :id and user_account_id = :user_account_id");
			query.setInteger("id", postId);
			query.setInteger("user_account_id", userAccountId);
			UserPost result = (UserPost) query.uniqueResult();			
			if (result != null) {
				query = session.createQuery(
						"update UserPost set description= :description,image_attachment= :image_attachment where id= :postId");

				// update user post description.
				if (userPost.getDescription() != null) {
					query.setString("description", userPost.getDescription());
				} else {
					query.setString("description", result.getDescription());
				}
				// update user image attachment.
				if (userPost.getDescription() != null) {
					query.setString("image_attachment", userPost.getImageAttachment());
				} else {
					query.setString("image_attachment", result.getImageAttachment());
				}
				query.setInteger("postId", postId);
				updatedRowCounter = query.executeUpdate();
				tr.commit();
				session.close();

			} else {
				throw new InvalidUserInputException("Trying to update invalid post.(wrong user / wrong post id)");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean delete(Integer userAccountId,Integer postId) {
		// TODO Auto-generated method stub
		int updatedRowCounter = 0;
		try {
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
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			Query query = session.createQuery("from UserPost where id= :id and user_account_id = :user_account_id");
			query.setInteger("id", postId);
			query.setInteger("user_account_id", userAccountId);
			UserPost result = (UserPost) query.uniqueResult();			
			System.out.println("**************************************************************************");
			System.out.println(query.getQueryString());
			System.out.println("**************************************************************************");
			if (result != null) {
				return result;
			} else {
				throw new InvalidUserInputException("Trying to read invalid post.(wrong user / wrong post id)");
			}
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			tr.commit();
			session.close();
			
		}
		return null;
	}

}
