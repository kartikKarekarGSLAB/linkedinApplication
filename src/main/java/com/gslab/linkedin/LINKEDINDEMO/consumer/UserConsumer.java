package com.gslab.linkedin.linkedindemo.consumer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private static final Logger logger = Logger.getLogger(UserConsumer.class.getName());

	@Override
	public void onMessage(Message message) {
		String newUserDetails = new String(message.getBody());
		try {
			UserVO messageUserVO = (UserVO) fromJson(newUserDetails);
			if (messageUserVO != null) {
				UserVO newUserProfile = userService.create(messageUserVO);
				logger.log(Level.INFO, newUserProfile.toString());
//				System.out.println(newUserProfile);
			}
		} catch (JsonParseException e) {
//			logger.setLevel(Level.WARNING);
//			logger.warning(e.getMessage());
			logger.log(Level.WARNING, e.getMessage(), e);
//			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.setLevel(Level.WARNING);
			logger.warning(e.getMessage());
//			e.printStackTrace();
		} catch (IOException e) {
			logger.setLevel(Level.WARNING);
			logger.warning(e.getMessage());
//			e.printStackTrace();
		} catch (InvalidUserInputException e) {
			logger.setLevel(Level.WARNING);
			logger.warning(e.getMessage());
//			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (CRUDOperationFailureException e) {
			logger.setLevel(Level.WARNING);
			logger.warning(e.getMessage());
//			System.out.println(e.getMessage());
			
//			e.printStackTrace();
		}
	}

	public Object fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		UserVO newUserDetails = new ObjectMapper().readValue(json, UserVO.class);
		return newUserDetails;
	}

}
