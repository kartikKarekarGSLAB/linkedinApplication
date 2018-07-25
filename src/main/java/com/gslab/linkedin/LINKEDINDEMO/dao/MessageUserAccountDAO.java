package com.gslab.linkedin.linkedindemo.dao;

import com.gslab.linkedin.linkedindemo.model.MessageUserAccount;

public interface MessageUserAccountDAO {
	public Integer create(MessageUserAccount messageUserAccount);

	public MessageUserAccount find(Integer userAccountId, Integer messageId);

	public boolean delete(Integer userAccountId, Integer messageId);
}
