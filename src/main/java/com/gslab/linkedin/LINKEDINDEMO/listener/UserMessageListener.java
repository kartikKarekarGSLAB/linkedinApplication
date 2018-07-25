package com.gslab.linkedin.linkedindemo.listener;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {
    
	public void receiveMessage(Map<String, String> message) {
		System.out.println(message.get(""));
    }
}
