/**
 * 


package io.jc.customer.common.mq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 197651
 *
 */
//@Configuration
/*public class RabbitMQConfig {
	
	@Value("${spring.rabbitmq.host}")
	String host;
	
	@Value("${spring.rabbitmq.port}")
	String port;
	
	@Value("${spring.rabbitmq.username}")
	String userName;
	
	@Value("${spring.rabbitmq.password}")
	String password;
	
	
	@Value("${jc.rabbitmq.queue}")
	String queueName;

	@Value("${jc.rabbitmq.exchange}")
	String exchange;

	@Value("${jc.rabbitmq.routingkey}")
	private String routingkey;
	
	 @Bean
	    public ConnectionFactory connectionFactory() {
	        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
	        connectionFactory.setUsername(userName);
	        connectionFactory.setPassword(password);
	        return connectionFactory;
	        
	        
	    }

	 @Bean
	    public Jackson2JsonMessageConverter messageConverter() {
	        ObjectMapper mapper = new ObjectMapper();
	        return new Jackson2JsonMessageConverter(mapper);
	    }
	 
	    @Bean
	    public RabbitTemplate rabbitTemplate() {
	    	 RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
	         rabbitTemplate.setMessageConverter(messageConverter());
	        return rabbitTemplate;
	    }

	    @Bean
	    public RabbitAdmin rabbitAdmin() {
	        return new RabbitAdmin(connectionFactory());
	    }

	} 
 */
