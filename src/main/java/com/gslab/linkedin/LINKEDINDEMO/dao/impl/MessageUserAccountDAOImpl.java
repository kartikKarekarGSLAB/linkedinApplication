package com.gslab.linkedin.linkedindemo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.MessageUserAccountDAO;
import com.gslab.linkedin.linkedindemo.model.Message;
import com.gslab.linkedin.linkedindemo.model.MessageUserAccount;

public class MessageUserAccountDAOImpl implements MessageUserAccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(MessageUserAccount messageUserAccount) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		int newUserId = (int) session.save(messageUserAccount);
		tr.commit();
		session.close();
		return newUserId;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer messageId) {
		int updatedRowCounter = 0;
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery(
				"delete from MessageUserAccount where user_account_id= :userAccountId and message_id=:messageId");
		query.setInteger("userAccountId", userAccountId);
		query.setInteger("messageId", messageId);
		updatedRowCounter = query.executeUpdate();
		tr.commit();
		session.close();
		if (updatedRowCounter == 1)
			return true;
		else
			return false;
	}

	@Override
	public MessageUserAccount find(Integer userAccountId, Integer messageId) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session
				.createQuery("from MessageUserAccount where user_account_id= :userAccountId and message_id=:messageId");
		query.setInteger("userAccountId", userAccountId);
		query.setInteger("messageId", messageId);
		MessageUserAccount existingMessageUserAccount = (MessageUserAccount) query.uniqueResult();
		tr.commit();
		session.close();
		return existingMessageUserAccount;
	}

	@Override
	public List<Message> findByCategory(Integer userAccountId, String category) {
		Session session = sessionFactory.openSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery(
				"from Message where id in (select message.id from MessageUserAccount where userAccount.id=:userAccountId and type=:type) order by createdOn desc");
		query.setInteger("userAccountId", userAccountId);
		query.setString("type", category);
		List<Message> userMessageList = query.list();
		tr.commit();
		session.close();
		return userMessageList;
	}

}
