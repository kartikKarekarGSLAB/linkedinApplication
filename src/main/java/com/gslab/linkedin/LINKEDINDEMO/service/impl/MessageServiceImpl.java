package com.gslab.linkedin.linkedindemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.linkedindemo.dao.MessageDAO;
import com.gslab.linkedin.linkedindemo.dao.MessageUserAccountDAO;
import com.gslab.linkedin.linkedindemo.dao.UserAccountDAO;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.Message;
import com.gslab.linkedin.linkedindemo.model.MessageUserAccount;
import com.gslab.linkedin.linkedindemo.model.UserAccount;
import com.gslab.linkedin.linkedindemo.model.vo.MessageVO;
import com.gslab.linkedin.linkedindemo.service.MessageService;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Autowired
	private MessageUserAccountDAO messageUserAccountDAO;

	@Override
	public MessageVO send(Integer userAccountId, MessageVO messageVO) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		// get sender account
		UserAccount senderUserAccount = userAccountDAO.findById(userAccountId);
		if (senderUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for sending message " + userAccountId);
		}
		// get receiver account
		UserAccount receiverUserAccount = userAccountDAO.findByUserName(messageVO.getReceiverUserName());
		if (receiverUserAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user name for sending message " + messageVO.getReceiverUserName());
		}

		Message sendMessage = new Message(messageVO.getMessage(), senderUserAccount.getUsername(),
				receiverUserAccount.getUsername(), date);
		Integer sendMessageId = messageDAO.create(sendMessage);
		sendMessage = messageDAO.findById(sendMessageId);

		MessageUserAccount sendMessageUserAccount = new MessageUserAccount(senderUserAccount, sendMessage, "send");
		Integer sendMessageUserAccountId = messageUserAccountDAO.create(sendMessageUserAccount);

		Message receiveMessage = new Message(messageVO.getMessage(), senderUserAccount.getUsername(),
				receiverUserAccount.getUsername(), date);
		Integer receiveMessageId = messageDAO.create(receiveMessage);
		receiveMessage = messageDAO.findById(receiveMessageId);

		MessageUserAccount receiveMessageUserAccount = new MessageUserAccount(receiverUserAccount, receiveMessage,
				"receive");
		Integer receiveMessageUserAccountId = messageUserAccountDAO.create(receiveMessageUserAccount);

		messageVO.setId(sendMessageUserAccountId);
		messageVO.setMessage(sendMessage.getMessage());
		messageVO.setReceiverUserName(sendMessage.getReceiverUserName());
		messageVO.setSenderUserName(sendMessage.getSenderUserName());
		messageVO.setType("send");
		return messageVO;
	}

	@Override
	public boolean delete(Integer userAccountId, Integer messageId) {

		// TODO Auto-generated method stub
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException("Invalid user account number for delete message " + userAccountId);
		}
		Message message = messageDAO.findById(messageId);
		if (message == null) {
			throw new InvalidUserInputException("Invalid message id for delete message " + messageId);
		}
		MessageUserAccount existingMessageUserAccount = messageUserAccountDAO.find(userAccountId, messageId);
		if (existingMessageUserAccount == null) {
			throw new InvalidUserInputException("Invalid message id " + messageId + " or user account id "
					+ userAccountId + " for  delete message ");
		}
		boolean messageUserAccountDelete = messageUserAccountDAO.delete(userAccountId, messageId);

		if (messageUserAccountDelete == false) {
			throw new InvalidUserInputException(
					"Fail to delete message with message id " + messageId + " and user account id " + userAccountId);
		}
		boolean messageDeleteResult = messageDAO.delete(messageId);
		if (messageDeleteResult == false) {
			throw new InvalidUserInputException(
					"Fail to delete message with message id " + messageId + " and user account id " + userAccountId);
		}
		return messageDeleteResult;
	}

	@Override
	public List<MessageVO> findByCategory(Integer userAccountId, String category) {
		UserAccount existingUserAccount = userAccountDAO.findById(userAccountId);
		if (existingUserAccount == null) {
			throw new InvalidUserInputException(
					"Invalid user account number for listing " + category + " message " + userAccountId);
		}
		if (!category.equalsIgnoreCase("inbox") && !category.equalsIgnoreCase("outbox")) {
			throw new InvalidUserInputException(
					"Invalid category '" + category + "' for listing message for user id" + userAccountId);
		}

		String type = "receive";
		if (category.equalsIgnoreCase("inbox")) {
			type = "receive";
		} else {
			type = "send";
		}
		List<MessageVO> messageVOList = new ArrayList<MessageVO>();
		for (Message message : messageUserAccountDAO.findByCategory(userAccountId, type)) {
			MessageVO messageVO = new MessageVO(message.getId(), message.getMessage(), type,
					message.getReceiverUserName(), message.getSenderUserName());
			messageVOList.add(messageVO);
		}
		return messageVOList;
	}

}
