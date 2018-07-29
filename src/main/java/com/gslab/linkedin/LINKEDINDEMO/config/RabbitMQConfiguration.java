package com.gslab.linkedin.linkedindemo.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gslab.linkedin.linkedindemo.consumer.UserConsumer;

@Configuration
public class RabbitMQConfiguration {

	private static final String MESSAGE_QUEUE = "linkedin-create-user";

	@Bean
	public Queue simpleQueue() {
		return new Queue(MESSAGE_QUEUE);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("linkedinpractice");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(MESSAGE_QUEUE);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
		return jackson2JsonMessageConverter;
	}

	@Bean
	public UserConsumer getUserConsumer() {
		return new UserConsumer();
	}

	@Bean
	public SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory);
		listenerContainer.setQueues(simpleQueue());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		listenerContainer.setMessageListener(getUserConsumer());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return listenerContainer;
	}
}
