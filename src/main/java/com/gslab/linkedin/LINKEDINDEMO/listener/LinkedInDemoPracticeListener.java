package com.gslab.linkedin.linkedindemo.listener;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class LinkedInDemoPracticeListener {
	
	@RabbitListener(queues="hello-message")
    public void receiveMessage(String message) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+message);
    }
}
