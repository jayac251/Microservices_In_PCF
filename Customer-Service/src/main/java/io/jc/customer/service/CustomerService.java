package io.jc.customer.service;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//import io.jc.customer.common.mq.config.RabbitMQSender;
import io.jc.customer.repo.Customer;
import io.jc.customer.repo.CustomerRepository;

/*
 * Service Class for Customer
 */
@Service
public class CustomerService {

	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private CustomerRepository customerRepository;
	
	//@Autowired
	//private RabbitMQSender rbtMQSender;

	@PostConstruct
	void initialize() {
		Customer cust1 = new Customer("123@gmail.com", "Girisha", "Yadav");
		Customer cust2 = new Customer("456@gmail.com", "Supraja", "Yadav");
		customerRepository.save(cust1);
		customerRepository.save(cust2);
	}

	/**
	 * Gets List of Customers from Customer Table
	 * 
	 * @return Customer List
	 * @throws Exception 
	 */
	 @HystrixCommand(fallbackMethod = "getCustomers_Fallback")
	public List<Customer> getCustomers() throws Exception {
		List<Customer> cust = new ArrayList<Customer>();
		try {
			cust = customerRepository.findAll();
			
		} catch (Exception e) {
			log.error("Customer Repository Returned no records");
			throw new Exception(e);
		}
		return cust;
	}

	/**
	 * Gets Customer of particular id from Customer Table
	 * 
	 * @return Customer
	 */
	public Optional<Customer> getCustomer(Long id) {
		Optional<Customer> customer;
		customer=	customerRepository.findById(id);
		if(customer.isPresent()) {
			log.info("Customer is present , id {}",id);
		}
		return customer;
	}

	/**
	 * Creates a new Customer
	 */
	public void createCustomer(Customer customer) {
		customerRepository.save(customer);
	//	rbtMQSender.send(customer);
		log.info("Customer Created");

	}

	 @SuppressWarnings("unused")
	private List<Customer> getCustomers_Fallback() {
		 
		 List<Customer> cust = new ArrayList<Customer>();
		 
	       log.error("CIRCUIT BREAKER" +  "Customer Service is down!!! fallback route enabled...");
	 
	        return cust;
	    }
}
