package com.gslab.linkedin.linkedindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gslab.linkedin.linkedindemo.model.vo.MessageVO;
import com.gslab.linkedin.linkedindemo.service.MessageService;

@RestController
@RequestMapping(value="/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/{accountid}/{type}",method=RequestMethod.GET)
	public String findAll(@PathVariable(name="accountid") Integer userAccountId,@PathVariable(name="type") String type) {
		return "Comment added with id:"+messageService.findAll(userAccountId, type);
	}
	
	@RequestMapping(value="/{accountid}",method=RequestMethod.POST)
	public String create(@PathVariable(name="accountid") Integer userAccountId,@RequestBody MessageVO messageVO) {
		return "Comment added with id:"+messageService.create(userAccountId, messageVO);
	}
	@RequestMapping(value="/{messageid}",method=RequestMethod.DELETE)
	public String delete(@PathVariable(name="messageid") Integer messageId) {
		return "Comment added with id:"+messageService.delete(messageId);
	}
	
}