package com.gslab.linkedin.linkedindemo;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkedInDemoApplicationConfig {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public SessionFactory getSessionFactory() {
		if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("Fail to create session factory instance inside configuration file");
		} else {
			return entityManagerFactory.unwrap(SessionFactory.class);
		}
		
	}
	
}
