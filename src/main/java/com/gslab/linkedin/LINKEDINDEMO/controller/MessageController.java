package com.gslab.linkedin.linkedindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.MessageVO;
import com.gslab.linkedin.linkedindemo.model.vo.ResponseBase;
import com.gslab.linkedin.linkedindemo.model.vo.Status;
import com.gslab.linkedin.linkedindemo.service.MessageService;

@RestController
@RequestMapping(value = "/users")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/{accountid}/{type}", method = RequestMethod.GET)
	public String findAll(@PathVariable(name = "accountid") Integer userAccountId,
			@PathVariable(name = "type") String type) {
		// TODO
		return "Comment added with id:" + messageService.findAll(userAccountId, type);
	}

	@RequestMapping(value = "/{userAccountId}/message", method = RequestMethod.POST)
	public ResponseBase sendMessage(@PathVariable(name = "userAccountId") Integer userAccountId,
			@RequestBody MessageVO messageVO) {
		messageVO = messageService.send(userAccountId, messageVO);
		return new ResponseBase(messageVO);
	}

	@RequestMapping(value = "/{userAccountId}/message/{messageid}", method = RequestMethod.DELETE)
	public ResponseBase delete(@PathVariable(name = "userAccountId") Integer userAccountId,
			@PathVariable(name = "messageid") Integer messageId) {
		messageService.delete(userAccountId, messageId);
		return new ResponseBase();
	}

}