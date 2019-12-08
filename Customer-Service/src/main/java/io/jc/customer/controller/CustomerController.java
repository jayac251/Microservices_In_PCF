package io.jc.customer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jc.customer.errorHandler.CustomerException;
import io.jc.customer.errorHandler.CustomerNotFoundException;
import io.jc.customer.repo.Customer;
import io.jc.customer.repo.CustomerRepository;
import io.jc.customer.service.CustomerService;
import io.jc.customer.util.InstanceInformationService;

@RestController
@RequestMapping("customer-service")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	InstanceInformationService instanceInfo;

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "customer", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String create(@RequestBody Customer customer) {

		customerService.createCustomer(customer);
		LOGGER.info("CustomerController:CreateCustomer: Environment {}" , instanceInfo.retrieveInstanceInfo() );
		return "Customer Created";
		
	}

	@GetMapping(path = "/customers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Customer> retrieveAllCustomers() throws CustomerException, CustomerNotFoundException {
		List<Customer> cust = new ArrayList<Customer>();
		try {
			cust = customerService.getCustomers();

			if (cust.isEmpty()) {
				throw new CustomerNotFoundException();
			}

		} catch (CustomerNotFoundException e) {
			throw new CustomerNotFoundException("No Customers are available in the table");
		}

		catch (Exception e) {
			throw new CustomerException("Exception while retrieving Customer Details ", e);
		}
		String env=instanceInfo.retrieveInstanceInfo();
		LOGGER.info("CustomerController:GET ALL Customers: Environment {}" , env );
		return cust;
	}

	@GetMapping(path = "/customer/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Customer retrieveStudent(@PathVariable Long id) throws CustomerNotFoundException, CustomerException {
		Optional<Customer> customer;
		try {
			customer = customerService.getCustomer(id);
			if (!customer.isPresent()) {
				throw new CustomerNotFoundException();
			}
		} catch (CustomerNotFoundException e) {
			throw new CustomerNotFoundException("Customer of id:" + id + " is not available in the table");
		} catch (Exception e) {
			throw new CustomerException("Exception while retrieving Customer Detail ", e);
		}
		LOGGER.info("CustomerController:GET Customer: Environment {}" , instanceInfo.retrieveInstanceInfo() );
		return customer.get();
	}
}
