package com.gslab.linkedin.linkedindemo.service;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.vo.MessageVO;

public interface MessageService {

	public MessageVO send(Integer userAccountId, MessageVO message);

	public List<MessageVO> findByCategory(Integer userAccountId, String category);

	public boolean delete(Integer userAccountId, Integer messageId);
}
