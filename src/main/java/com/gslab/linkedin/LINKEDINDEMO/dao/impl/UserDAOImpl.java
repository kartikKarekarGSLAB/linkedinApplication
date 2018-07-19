package com.gslab.linkedin.LINKEDINDEMO.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.gslab.linkedin.LINKEDINDEMO.dao.UserDAO;
import com.gslab.linkedin.LINKEDINDEMO.model.UserAccount;
import com.gslab.linkedin.LINKEDINDEMO.model.UserProfileInfo;
import com.gslab.linkedin.LINKEDINDEMO.model.vo.UserVO;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Integer create(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO finaById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO update(UserVO userVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
