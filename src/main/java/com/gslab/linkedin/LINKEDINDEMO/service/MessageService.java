package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.MessageVO;

public interface MessageService {
	public Integer create(Integer userAccountId,MessageVO message);
	public List<MessageVO> findAll(Integer userAccountId,String type);
	public boolean delete(Integer messageId);
}
