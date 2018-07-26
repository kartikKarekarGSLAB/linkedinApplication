package com.gslab.linkedin.linkedindemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gslab.linkedin.linkedindemo.listener.LinkedInDemoPracticeListener;

@Configuration
public class RabbitMQConfiguration {
	public final static String SFG_MESSAGE_QUEUE = "hello-message";

	@Bean
	Queue queue() {
		return new Queue(SFG_MESSAGE_QUEUE, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("linkedinpractice");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_QUEUE);
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(SFG_MESSAGE_QUEUE);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(LinkedInDemoPracticeListener receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
