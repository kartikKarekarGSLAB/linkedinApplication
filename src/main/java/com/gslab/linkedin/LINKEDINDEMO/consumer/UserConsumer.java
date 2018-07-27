package com.gslab.linkedin.linkedindemo.consumer;

import java.io.IOException;

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

	@Override
	public void onMessage(Message message) {
		String newUserDetails = new String(message.getBody());
		try {
			UserVO messageUserVO = (UserVO) fromJson(newUserDetails);
			if (messageUserVO != null) {
				UserVO newUserProfile = userService.create(messageUserVO);
				System.out.println(newUserProfile);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidUserInputException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (CRUDOperationFailureException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		}
	}

	public Object fromJson(String json) throws JsonParseException, JsonMappingException, IOException {
		UserVO newUserDetails = new ObjectMapper().readValue(json, UserVO.class);
		return newUserDetails;
	}

}
