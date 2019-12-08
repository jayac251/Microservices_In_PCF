package io.jc.customer.common.mq.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jc.customer.repo.Customer;

@Service
public class RabbitMQSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void send(Customer customer) {
		rabbitTemplate.convertAndSend("mqQueue", customer);
		System.out.println("Send msg = " + customer);

	}
}