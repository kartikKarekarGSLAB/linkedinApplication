package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.Message;
import com.gslab.linkedin.linkedindemo.model.MessageUserAccount;

public interface MessageUserAccountDAO {
	public Integer create(MessageUserAccount messageUserAccount);

	public MessageUserAccount find(Integer userAccountId, Integer messageId);
	
	public List<Message> findByCategory(Integer userAccountId,String category);

	public boolean delete(Integer userAccountId, Integer messageId);
}
