package com.gslab.linkedin.linkedindemo.dao;

import java.util.List;

import com.gslab.linkedin.linkedindemo.model.Message;

public interface MessageDAO {
	public Integer create(Message message);
	public List<Message> findAll(Integer userAccountId,String type);
	public boolean delete(Integer messageId);
}
