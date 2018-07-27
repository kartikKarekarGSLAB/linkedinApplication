package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.MessageDAO;
import com.gslab.linkedin.linkedindemo.model.Message;

public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(Message message) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(message);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public List<Message> findAll(Integer userAccountId, String type) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery(
				"from Message where id in (select message_id from MessageUserAccount where user_account_id = :userAccountId)");
		query.setInteger("user_account_id", userAccountId);
		List<Message> userPostList = query.list();
		tr.commit();
		session.close();
		return userPostList;
	}

	@Override
	public boolean delete(Integer messageId) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("delete from Message where id= :id");
		query.setInteger("id", messageId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public Message findById(Integer messageId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from Message where id= :id");
		query.setInteger("id", messageId);
		Message message = (Message) query.uniqueResult();
		tr.commit();
		session.close();
		return message;
	}

}
