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
	
	public MessageVO create(Integer userAccountId, MessageVO messageVO) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		UserAccount userAccount = userAccountDAO.findById(userAccountId);
		if (userAccount != null) {
			if (messageVO.getType().equalsIgnoreCase("send") || messageVO.getType().equalsIgnoreCase("receive")) {
				if (!messageVO.getMessage().isEmpty()) {
					UserAccount receiverUserAccount = userAccountDAO.findByUserName(messageVO.getReceiverUserName());
					if (receiverUserAccount != null) {						
						Message senderMessage = new Message();
						senderMessage.setMessage(messageVO.getMessage());
						senderMessage.setCreatedOn(date);
						//sender entry.
						senderMessage.setType("send");
						senderMessage.setReceiverUserName(messageVO.getReceiverUserName());
						senderMessage.setSenderUserName(userAccount.getUsername());
						(senderMessage.getUserAccount()).add(userAccount);
						Integer sendMessageID = messageDAO.create(senderMessage);
						//receiver entry
						Message receiverMessage = new Message();
						receiverMessage.setMessage(messageVO.getMessage());
						receiverMessage.setCreatedOn(date);
						//sender entry.
						receiverMessage.setType("receive");
						receiverMessage.setReceiverUserName(messageVO.getReceiverUserName());
						receiverMessage.setSenderUserName(userAccount.getUsername());
						(receiverMessage.getUserAccount()).add(receiverUserAccount);
						messageDAO.create(receiverMessage);
					} else {
						throw new InvalidUserInputException("invalid receiver username specified."+messageVO.getReceiverUserName());
					}
				} else {
					throw new InvalidUserInputException("unable to send Empty message.");
				}
			} else {
				throw new InvalidUserInputException("Wrong Message type.It should be either \"send\" or \"receive\". ");
			}

		} else {
			throw new InvalidUserInputException("Invalid user account number for message");
		}
		return messageVO;
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
