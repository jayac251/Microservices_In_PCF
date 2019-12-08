package io.jc.sales.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jc.sales.errorHandler.OrderNotFoundException;
import io.jc.sales.errorHandler.SalesOrderException;
import io.jc.sales.repo.SalesOrder;
import io.jc.sales.service.SalesOrderService;

@RestController
@RequestMapping("sales-order")
public class SalesOrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderController.class);

	
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	@RequestMapping(value = "create-order", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public String create(@RequestBody SalesOrder sale) throws SalesOrderException {
		String status = "Sales Order not created, Please validate the request";
		if (sale != null) {			
			String saleStatus = salesOrderService.createSalesOrder(sale);
			if(StringUtils.contains(saleStatus, "Failed")) {
				throw new SalesOrderException("Unable to create order: "+saleStatus);
			}
			status = "Sales Order Successfully Created";
		}
		return status;
	}
	
	
	
	
	@GetMapping(path = "/get-orders", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SalesOrder> retrieveAllOrders() throws SalesOrderException, OrderNotFoundException {
		List<SalesOrder> sales = new ArrayList<SalesOrder>();
		try {
			sales = salesOrderService.getAllSalesOrders();

			if (sales.isEmpty()) {
				throw new OrderNotFoundException();
			}

		} catch (OrderNotFoundException e) {
			throw new OrderNotFoundException("No Orders are available in the table");
		}

		catch (Exception e) {
			throw new SalesOrderException("Exception while retrieving Order Details ", e);
		}
		return sales;
	}
}
