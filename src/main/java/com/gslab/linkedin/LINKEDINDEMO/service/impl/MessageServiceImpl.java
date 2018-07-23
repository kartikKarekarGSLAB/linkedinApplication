package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.MessageDAO;
import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.Message;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.UserComment;
import com.gslab.linkedin.linkedindemo.model.UserPost;
import com.gslab.linkedin.linkedindemo.model.vo.MessageVO;
import com.gslab.linkedin.linkedindemo.service.MessageService;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Override
	public Integer create(Integer userAccountId, MessageVO message) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			if (message.getType().equalsIgnoreCase("send") || message.getType().equalsIgnoreCase("receive")) {
				if (message.getMessage() != null) {
					Message newMessage = new Message();
					newMessage.setMessage(message.getMessage());
					newMessage.setType(message.getType());
					newMessage.setCreatedOn(date);
					(newMessage.getUserAccount()).add(userAccount);
					return messageDAO.create(newMessage);
				} else {
					throw new InvalidUserInputException("Wrong Message type");
				}
			} else {
				throw new InvalidUserInputException("Wrong Message type");
			}

		} else {
			throw new InvalidUserInputException("Invalid user account number for message");
		}
	}

	@Override
	public List<MessageVO> findAll(Integer userAccountId, String type) {
		// TODO Auto-generated method stub
		for (Message m : messageDAO.findAll(userAccountId, "send")) {
			System.out.println(m.getMessage());
		}
		return null;
	}

	@Override
	public boolean delete(Integer messageId) {
		// TODO Auto-generated method stub
		return messageDAO.delete(messageId);
	}

}
