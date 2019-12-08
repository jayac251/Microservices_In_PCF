package io.jc.sales.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.jc.sales.controller.SalesOrderController;
import io.jc.sales.repo.CustomerRepository;
import io.jc.sales.repo.CustomerSOS;
import io.jc.sales.repo.Item;
import io.jc.sales.repo.OrderLineItems;
import io.jc.sales.repo.SalesOrder;
import io.jc.sales.repo.SalesOrderRepository;

@Service
public class SalesOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderService.class);
	
	@Autowired
	LoadBalancerClient loadBalancer;

	@Autowired
	private SalesOrderRepository sosRepository;

	@Autowired
	CustomerRepository custRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RestHandler restHandler;

	@Value("${jc.item.service.id}")
	String serviceId;
	
	@Value("${jc.item.service.endpoint}")
	String serviceEndpoint;
	
	
	@PostConstruct
	void initialize() {
		PopulateTables();
	}

	public List<SalesOrder> getAllSalesOrders() throws Exception {
		List<SalesOrder> sales = new ArrayList<SalesOrder>();
		try {
			sales = sosRepository.findAll();
		} catch (Exception e) {

			throw new Exception(e);
		}
		return sales;
	}

	 @HystrixCommand(fallbackMethod = "createSales_Fallback")
	public String createSalesOrder(SalesOrder order) {
		//Get Service Instance from Ribbon
		ServiceInstance serviceInstance = loadBalancer.choose(serviceId);
	
	System.out.println(serviceInstance.getUri());
	
	String baseUrl=serviceInstance.getUri().toString()+serviceEndpoint;
	
	LOGGER.info("ITEM SERVICE URL CALLED: "+ baseUrl);
		
		
		String status = "";

		if (order != null) {
			if (order.getCustId() != null) {
				boolean customerExists = custRepository.existsById(order.getCustId());
				if (customerExists) {
					List<OrderLineItems> orderLineItems = order.getOrderLineItems();
					if (!orderLineItems.isEmpty()) {

						// Get Items details of the Order
						List<String> itemNameInOrder = orderLineItems.stream().map(OrderLineItems::getItemName)
								.collect(Collectors.toList());

						if (!itemNameInOrder.isEmpty()) {

							// Call Item Service to get the items available in inventory
							List<Item> listOfItems = (List<Item>) restHandler.callRestService((restTemplate) -> {
								Item[] items = restTemplate.getForObject(baseUrl, Item[].class);
								return Arrays.asList(items);
							});

							// Filter all the Item names available in inventory
							List<String> itemName = listOfItems.stream().map(Item::getName)
									.collect(Collectors.toList());
							// Check if Items present in Item inventory
							List<String> unavailableItems = itemNameInOrder.stream().filter(e -> !itemName.contains(e))
									.collect(Collectors.toList());

							if (unavailableItems.isEmpty()) {
								// If items available in Items Inventory, save the order to Sales-order table
								sosRepository.save(order);
								status = "Order Processed Successfully";
							} else {
								status = "Failed, " + unavailableItems.toString()
										+ " not available in Item Inventory, revisit your Order";
							}

						} else {
							status = "Failed, No Items present in the order request";
						}

					} else {
						status = "Failed, No Items present in the order request";
					}
				}

			} else {
				status = "Failed, Customer Id not available";
			}

		}
		return status;

	}
	 
	 @SuppressWarnings("unused")
	private String createSales_Fallback(SalesOrder order) {
		 String status="Failed, Service Unavailable Currently, Try Again Later";
		 LOGGER.info("Hystrix Callback called due to Item service unavailable");
		 
		 return status;
	    }

	private void PopulateTables() {
		CustomerSOS cust1 = new CustomerSOS("123@gmail.com", "Girisha", "Yadav");
		CustomerSOS cust2 = new CustomerSOS("456@gmail.com", "Supraja", "Yadav");
		custRepository.save(cust1);
		custRepository.save(cust2);

		OrderLineItems orderLineItem1 = new OrderLineItems("Colgate ToothPaste", "2");
		OrderLineItems orderLineItem2 = new OrderLineItems("Himalaya Powder", "1");
		OrderLineItems orderLineItem3 = new OrderLineItems("Lux Soap", "1");

		OrderLineItems orderLineItem4 = new OrderLineItems("Ponds", "2");
		OrderLineItems orderLineItem5 = new OrderLineItems("Johnsons", "1");

		List<OrderLineItems> listOfItems1 = new ArrayList<OrderLineItems>();
		listOfItems1.add(orderLineItem1);
		listOfItems1.add(orderLineItem2);
		listOfItems1.add(orderLineItem3);

		List<OrderLineItems> listOfItems2 = new ArrayList<OrderLineItems>();
		listOfItems2.add(orderLineItem4);
		listOfItems2.add(orderLineItem5);

		SalesOrder order1 = new SalesOrder(LocalDateTime.now().toString(), new Long(1), "Order 1", "100", listOfItems1);
		SalesOrder order2 = new SalesOrder(LocalDateTime.now().toString(), new Long(1), "Order 2", "200", listOfItems2);
		sosRepository.save(order1);
		sosRepository.save(order2);

	}
}
