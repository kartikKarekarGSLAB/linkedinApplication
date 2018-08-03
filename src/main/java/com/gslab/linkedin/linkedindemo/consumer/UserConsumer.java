package com.gslab.linkedin.linkedindemo.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gslab.linkedin.linkedindemo.exception.CRUDOperationFailureException;
import com.gslab.linkedin.linkedindemo.exception.InvalidUserInputException;
import com.gslab.linkedin.linkedindemo.model.vo.UserVO;
import com.gslab.linkedin.linkedindemo.service.UserService;

public class UserConsumer implements MessageListener {

	@Autowired
	private UserService userService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

	@Override
	public void onMessage(Message message) {
		String newUserDetails = new String(message.getBody());

		try {
			if (newUserDetails.isEmpty()) {
				LOGGER.error("Empty message from rabbitMQ.", new RuntimeException("Empty message not accpeted"));
			}
			LOGGER.info("inside on Message");
			UserVO messageUserVO = (UserVO) fromJson(newUserDetails);
			if (messageUserVO != null) {
				UserVO newUserProfile = userService.create(messageUserVO);
				LOGGER.info(newUserProfile.toString());
			}
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvalidUserInputException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (CRUDOperationFailureException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public Object fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		UserVO newUserDetails = new ObjectMapper().readValue(json, UserVO.class);
		return newUserDetails;
	}

}
